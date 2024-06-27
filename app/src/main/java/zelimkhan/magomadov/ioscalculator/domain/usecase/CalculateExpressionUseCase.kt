package zelimkhan.magomadov.ioscalculator.domain.usecase

import zelimkhan.magomadov.ioscalculator.domain.service.CalculatorService

class CalculateExpressionUseCase(
    private val calculatorService: CalculatorService
) {
    operator fun invoke(expression: String): String {
        val removedExtraDots = expression.replace("\\.(?![0-9])".toRegex(), "")
        val evaluatedException = calculatorService.calculate(removedExtraDots)

        var result = evaluatedException
        if (evaluatedException.contains('.')) {
            val fractionPart = evaluatedException.substringAfter('.')
            if (fractionPart.any { it == '0' }) {
                result = evaluatedException.dropLast(fractionPart.length + 1)
            }
        }

        return result
    }
}