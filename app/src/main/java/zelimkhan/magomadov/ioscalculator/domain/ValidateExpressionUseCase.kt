package zelimkhan.magomadov.ioscalculator.domain

private const val MAX_LENGTH = 15

class ValidateExpressionUseCase {
    operator fun invoke(expression: String): Boolean {
        if (expression.length > MAX_LENGTH)
            return false

        if (expression.count { it == '.' } > 1)
            return false

        return true
    }
}