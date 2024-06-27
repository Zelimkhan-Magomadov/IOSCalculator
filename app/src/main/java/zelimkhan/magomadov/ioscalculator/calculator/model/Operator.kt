package zelimkhan.magomadov.ioscalculator.calculator.model

sealed class Operator(val symbol: String = "") {
    data object Clean : Operator()
    data object UnaryPlusMinus : Operator()
    data object Percent : Operator()
    data object Divide : Operator("/")
    data object Multiply : Operator("*")
    data object Minus : Operator("-")
    data object Plus : Operator("+")
    data object Equals : Operator()
}