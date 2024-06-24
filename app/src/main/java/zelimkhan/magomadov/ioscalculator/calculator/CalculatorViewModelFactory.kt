package zelimkhan.magomadov.ioscalculator.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import zelimkhan.magomadov.ioscalculator.domain.CalculateExpressionUseCase
import zelimkhan.magomadov.ioscalculator.domain.ValidateExpressionUseCase

class CalculatorViewModelFactory(
    private val validateExpressionUseCase: ValidateExpressionUseCase,
    private val calculateExpressionUseCase: CalculateExpressionUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CalculatorViewModel(validateExpressionUseCase, calculateExpressionUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}