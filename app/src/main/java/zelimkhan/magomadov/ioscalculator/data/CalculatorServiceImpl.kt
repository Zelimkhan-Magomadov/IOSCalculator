package zelimkhan.magomadov.ioscalculator.data

import org.mariuszgromada.math.mxparser.Expression
import zelimkhan.magomadov.ioscalculator.domain.service.CalculatorService

class CalculatorServiceImpl : CalculatorService {
    override fun calculate(expression: String): String {
        return Expression(expression).calculate().toString()
    }
}