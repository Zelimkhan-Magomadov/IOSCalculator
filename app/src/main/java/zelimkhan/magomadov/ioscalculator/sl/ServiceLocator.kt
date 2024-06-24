package zelimkhan.magomadov.ioscalculator.sl

import zelimkhan.magomadov.ioscalculator.calculator.CalculatorViewModelFactory
import zelimkhan.magomadov.ioscalculator.data.MxparserCalculatorService
import zelimkhan.magomadov.ioscalculator.domain.CalculateExpressionUseCase
import zelimkhan.magomadov.ioscalculator.domain.ValidateExpressionUseCase

object ServiceLocator {
    private val calculatorService by lazy { MxparserCalculatorService() }

    private val validateExpressionUseCase by lazy { ValidateExpressionUseCase() }
    private val calculateExpressionUseCase by lazy { CalculateExpressionUseCase(calculatorService) }

    fun provideCalculatorViewModelFactory(): CalculatorViewModelFactory {
        return CalculatorViewModelFactory(validateExpressionUseCase, calculateExpressionUseCase)
    }
}