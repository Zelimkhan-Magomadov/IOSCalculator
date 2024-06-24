package zelimkhan.magomadov.ioscalculator.data

import org.mariuszgromada.math.mxparser.Expression
import zelimkhan.magomadov.ioscalculator.domain.CalculatorService

class MxparserCalculatorService : CalculatorService {
    override fun calculate(expression: String): String {
        return Expression(expression).calculate().toString()
    }
}