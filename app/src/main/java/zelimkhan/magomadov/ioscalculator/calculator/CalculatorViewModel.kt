package zelimkhan.magomadov.ioscalculator.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zelimkhan.magomadov.ioscalculator.calculator.model.ClearState
import zelimkhan.magomadov.ioscalculator.calculator.model.Operator
import zelimkhan.magomadov.ioscalculator.calculator.state.CalculatorIntent
import zelimkhan.magomadov.ioscalculator.calculator.state.CalculatorState
import zelimkhan.magomadov.ioscalculator.domain.usecase.CalculateExpressionUseCase
import zelimkhan.magomadov.ioscalculator.domain.usecase.ValidateExpressionUseCase
import java.text.NumberFormat
import java.util.Locale
import java.util.Stack

class CalculatorViewModel(
    val validateExpressionUseCase: ValidateExpressionUseCase,
    val calculateExpressionUseCase: CalculateExpressionUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state = _state.asStateFlow()

    private var expression = ""
    private var previousOperator: Operator? = null

    private val deletedCharacters = Stack<Char>()

    fun processIntent(intent: CalculatorIntent) {
        viewModelScope.launch {
            when (intent) {
                CalculatorIntent.Clear -> clear()
                CalculatorIntent.ToggleSign -> toggleSign()
                CalculatorIntent.Percent -> calculatePercent()
                is CalculatorIntent.Number -> appendNumber(intent.number)
                is CalculatorIntent.ArithmeticOperator -> appendOperator(intent.operator)
                CalculatorIntent.Evaluate -> evaluate()
                CalculatorIntent.DeleteLastCharacter -> deleteLastCharacter()
                CalculatorIntent.RestoreLastCharacter -> restoreLastCharacter()
            }
        }
    }

    private fun deleteLastCharacter() {
        _state.update { state ->
            val result = if (state.currentInput.length - 1 == 0) {
                "0"
            } else {
                deletedCharacters.push(state.currentInput.last())
                state.currentInput.dropLast(1)
            }

            state.copy(currentInput = result)
        }
    }

    private fun restoreLastCharacter() {
        if (deletedCharacters.empty()) return
        appendNumber(deletedCharacters.pop().toString())
    }

    private fun clear() {
        _state.update { state ->
            expression = when (state.clearState) {
                is ClearState.ClearCurrent -> expression.removeSuffix(state.currentInput)
                ClearState.ClearAll -> ""
            }

            state.copy(currentInput = "0", currentOperator = null, clearState = ClearState.ClearAll)
        }
    }

    private fun toggleSign() {
        _state.update { state ->
            var result = state.currentInput
            result = if (result.startsWith('-')) result.drop(1) else "-$result"

            expression = expression.removeSuffix(state.currentInput) + result
            previousOperator = Operator.UnaryPlusMinus

            state.copy(currentInput = result, currentOperator = null)
        }
    }

    private fun calculatePercent() {
        _state.update { state ->
            val percent = calculateExpressionUseCase("${state.currentInput} / 100")

            expression += " $percent "
            previousOperator = Operator.Percent

            state.copy(currentInput = percent, currentOperator = null)
        }
    }

    private fun appendNumber(number: String) {
        _state.update { state ->
            val result = when {
                state.currentOperator != null && number == "." -> "0."
                state.currentInput == "0" && number != "." -> number
                state.currentOperator != null -> number
                else -> {
                    val isValid = validateExpressionUseCase(state.currentInput + number)
                    if (isValid.not()) return
                    state.currentInput + number
                }
            }

            expression += number
            state.copy(
                currentInput = formatNumber(result),
                currentOperator = null,
                clearState = ClearState.ClearCurrent
            )
        }
    }

    private fun appendOperator(operator: Operator) {
        _state.update { state ->
            expression = if (state.currentOperator != null) {
                val index = expression.lastIndexOf(state.currentOperator.symbol)
                expression.replaceRange(index, index + 1, operator.symbol)
            } else {
                " ${state.currentInput} ${operator.symbol} "
            }

            previousOperator = operator
            state.copy(currentOperator = operator)
        }
    }

    private fun evaluate() {
        _state.update { state ->
            if (previousOperator is Operator.Equals) {
                return
            }

            if (state.currentOperator != null) {
                expression += state.currentInput
            }

            Log.d("expression", "Expression: $expression")
            val result = calculateExpressionUseCase(expression.withoutSpaces)

            expression = ""
            previousOperator = Operator.Equals
            state.copy(
                currentInput = formatNumber(result),
                currentOperator = null,
                clearState = ClearState.ClearAll
            )
        }
    }

    private fun formatNumber(number: String): String {
        return try {
            val parts = number.split(".")
            val integerPart = parts[0].withoutSpaces.toBigDecimal()
            val fractionalPart = if (parts.size > 1) ".${parts[1]}" else ""

            val locale = Locale("ru")
            val formattedNumber = NumberFormat.getInstance(locale).format(integerPart)

            formattedNumber + fractionalPart
        } catch (e: Exception) {
            number
        }
    }

    private val String.withoutSpaces get() = this.replace("\\s".toRegex(), "")
}