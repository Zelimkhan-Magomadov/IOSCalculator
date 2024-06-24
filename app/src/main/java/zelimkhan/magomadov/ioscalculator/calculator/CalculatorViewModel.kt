package zelimkhan.magomadov.ioscalculator.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mariuszgromada.math.mxparser.Expression
import zelimkhan.magomadov.ioscalculator.domain.CalculateExpressionUseCase
import zelimkhan.magomadov.ioscalculator.domain.ValidateExpressionUseCase

class CalculatorViewModel(
    val validateExpressionUseCase: ValidateExpressionUseCase,
    val calculateExpressionUseCase: CalculateExpressionUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state = _state.asStateFlow()

    private var expression = ""
    private var previousOperator: Operator? = null

    fun processIntent(intent: CalculatorIntent) {
        viewModelScope.launch {
            when (intent) {
                is CalculatorIntent.EnterDigit -> processEnterDigit(intent.digit)
                CalculatorIntent.EnterDot -> processEnterDot()
                is CalculatorIntent.OperatorClick -> processOperatorClick(intent.operator)
            }
        }
    }

    private fun processEnterDigit(digit: Char) {
        _state.update { state ->
            val isValid = validateExpressionUseCase(state.currentInput + digit)
            if (isValid.not()) return

            val newValue = when {
                state.currentOperator != null -> digit.toString()
                state.currentInput == "0" -> digit.toString()
                else -> state.currentInput + digit
            }

            state.copy(currentInput = newValue, currentOperator = null)
        }
    }

    private fun processEnterDot() {
        _state.update { state ->
            val isValid = validateExpressionUseCase(state.currentInput + '.')
            if (isValid.not()) return

            val newValue = state.currentInput + '.'

            state.copy(currentInput = newValue, currentOperator = null)
        }
    }

    private fun processOperatorClick(operator: Operator) {
        _state.update { state ->
            val currentInput = state.currentInput
            val currentOperator = state.currentOperator

            if (previousOperator == Operator.Percent) {
                expression += " * $currentInput / 100"
            } else if (currentOperator != null && operator != Operator.Equals) {
                Log.d("expression", "Drop: $expression")
                expression = expression.dropLast(3)
            } else {
                expression += currentInput
            }

            previousOperator = operator

            when (operator) {
                Operator.Clean -> {
                    expression = ""
                    state.copy(currentInput = "0", currentOperator = null)
                }

                Operator.UnaryPlusMinus -> {
                    val newValue = when {
                        currentInput.startsWith('-') -> currentInput.drop(1)
                        else -> "-$currentInput"
                    }
                    state.copy(currentInput = newValue, currentOperator = operator)
                }

                Operator.Percent -> state.copy(currentOperator = operator)

                Operator.Divide,
                Operator.Multiply,
                Operator.Minus,
                Operator.Plus -> {
                    expression += " ${operator.symbol} "
                    state.copy(currentOperator = operator)
                }

                Operator.Equals -> {
                    Log.d("expression", "Expression: $expression")
                    val result = calculateExpressionUseCase(expression)
                    expression = ""
                    state.copy(currentInput = result, currentOperator = Operator.Equals)
                }
            }
        }
    }
}