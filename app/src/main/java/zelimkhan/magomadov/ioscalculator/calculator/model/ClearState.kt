package zelimkhan.magomadov.ioscalculator.calculator.model

sealed class ClearState(val text: String) {
    data object ClearAll : ClearState("AC")
    data object ClearCurrent : ClearState("C")
}