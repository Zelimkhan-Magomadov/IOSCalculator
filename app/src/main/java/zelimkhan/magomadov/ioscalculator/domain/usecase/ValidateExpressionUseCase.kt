package zelimkhan.magomadov.ioscalculator.domain.usecase

class ValidateExpressionUseCase {
    companion object {
        private const val MAX_LENGTH = 15
    }

    operator fun invoke(expression: String): Boolean {
        if (expression.length > MAX_LENGTH) {
            return false
        }

        if (expression.count { it == '.' } > 1) {
            return false
        }

        return true
    }
}