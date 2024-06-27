package zelimkhan.magomadov.ioscalculator.calculator.state

import zelimkhan.magomadov.ioscalculator.calculator.model.ClearState
import zelimkhan.magomadov.ioscalculator.calculator.model.Operator

data class CalculatorState(
    val currentInput: String = "0",
    val currentOperator: Operator? = null,
    val clearState: ClearState = ClearState.ClearAll
)