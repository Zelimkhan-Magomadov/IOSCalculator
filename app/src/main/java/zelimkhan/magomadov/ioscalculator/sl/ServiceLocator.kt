package zelimkhan.magomadov.ioscalculator.sl

import zelimkhan.magomadov.ioscalculator.calculator.CalculatorViewModelFactory
import zelimkhan.magomadov.ioscalculator.data.CalculatorServiceImpl
import zelimkhan.magomadov.ioscalculator.domain.usecase.CalculateExpressionUseCase
import zelimkhan.magomadov.ioscalculator.domain.usecase.ValidateExpressionUseCase

object ServiceLocator {
    private val calculatorService by lazy { CalculatorServiceImpl() }

    private val validateExpressionUseCase by lazy { ValidateExpressionUseCase() }
    private val calculateExpressionUseCase by lazy { CalculateExpressionUseCase(calculatorService) }

    fun provideCalculatorViewModelFactory(): CalculatorViewModelFactory {
        return CalculatorViewModelFactory(validateExpressionUseCase, calculateExpressionUseCase)
    }
}