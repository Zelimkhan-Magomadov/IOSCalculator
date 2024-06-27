package zelimkhan.magomadov.ioscalculator.calculator.state

import zelimkhan.magomadov.ioscalculator.calculator.model.Operator

sealed interface CalculatorIntent {
    data class Number(val number: String) : CalculatorIntent
    data class ArithmeticOperator(val operator: Operator) : CalculatorIntent
    data object Clear : CalculatorIntent
    data object ToggleSign : CalculatorIntent
    data object Percent : CalculatorIntent
    data object Evaluate : CalculatorIntent
    data object DeleteLastCharacter : CalculatorIntent
    data object RestoreLastCharacter : CalculatorIntent
}