package zelimkhan.magomadov.ioscalculator.domain

class CalculateExpressionUseCase(
    private val calculatorService: CalculatorService
) {
    operator fun invoke(expression: String): String {
        val evaluatedException = calculatorService.calculate(expression)
        
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
