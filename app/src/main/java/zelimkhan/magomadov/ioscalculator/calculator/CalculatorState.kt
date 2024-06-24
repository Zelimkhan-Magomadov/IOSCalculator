package zelimkhan.magomadov.ioscalculator.calculator

data class CalculatorState(
    val currentInput: String = "0",
    val currentOperator: Operator? = null
)

sealed class Operator(val symbol: Char) {
    data object Clean : Operator('C')
    data object UnaryPlusMinus : Operator('±')
    data object Percent : Operator('%')
    data object Divide : Operator('/')
    data object Multiply : Operator('*')
    data object Minus : Operator('-')
    data object Plus : Operator('+')
    data object Equals : Operator('=')
}


