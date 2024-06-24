package zelimkhan.magomadov.ioscalculator.calculator

sealed interface CalculatorIntent {
    data class EnterDigit(val digit: Char) : CalculatorIntent
    data object EnterDot : CalculatorIntent
    data class OperatorClick(val operator: Operator) : CalculatorIntent
}