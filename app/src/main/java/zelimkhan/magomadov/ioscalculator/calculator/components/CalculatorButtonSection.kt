package zelimkhan.magomadov.ioscalculator.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zelimkhan.magomadov.ioscalculator.R
import zelimkhan.magomadov.ioscalculator.calculator.model.ClearState
import zelimkhan.magomadov.ioscalculator.calculator.model.Operator
import zelimkhan.magomadov.ioscalculator.calculator.state.CalculatorIntent
import zelimkhan.magomadov.ioscalculator.ui.theme.IOSCalculatorFontFamily

@Composable
fun CalculatorButtonSection(
    buttonSpacing: Dp,
    buttonWidth: Dp,
    buttonHeight: Dp,
    modifier: Modifier = Modifier,
    selectedOperator: Operator? = null,
    clearState: ClearState = ClearState.ClearAll,
    onIntent: (CalculatorIntent) -> Unit = {}
) {
    val operatorColor = MaterialTheme.colorScheme.primary
    val selectedOperatorColor = Color(0xFFFFFFFF)
    val operatorIconColor = MaterialTheme.colorScheme.onPrimary
    val selectedOperatorIconColor = MaterialTheme.colorScheme.primary

    Column(
        modifier = modifier.padding(buttonSpacing),
        verticalArrangement = Arrangement.spacedBy(buttonSpacing)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.secondary,
                text = clearState.text,
                fontSize = 32.sp,
                fontFamily = IOSCalculatorFontFamily.Inter,
                fontWeight = FontWeight.Medium,
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Clear
                    onIntent(intent)
                }
            )

            CalculatorIconButton(
                buttonColor = MaterialTheme.colorScheme.secondary,
                drawableRes = R.drawable.unary_plus_minus,
                drawableColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.ToggleSign
                    onIntent(intent)
                }
            )

            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.secondary,
                text = "%",
                fontFamily = IOSCalculatorFontFamily.Inter,
                fontWeight = FontWeight.Medium,
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Percent
                    onIntent(intent)
                }
            )

            CalculatorIconButton(
                buttonColor = if (selectedOperator is Operator.Divide) selectedOperatorColor else operatorColor,
                drawableRes = R.drawable.divide,
                drawableColor = if (selectedOperator is Operator.Divide) selectedOperatorIconColor else operatorIconColor,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.ArithmeticOperator(Operator.Divide)
                    onIntent(intent)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = "7",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number("7")
                    onIntent(intent)
                }
            )

            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = "8",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number("8")
                    onIntent(intent)
                }
            )

            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = "9",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number("9")
                    onIntent(intent)
                }
            )

            CalculatorIconButton(
                buttonColor = if (selectedOperator is Operator.Multiply) selectedOperatorColor else operatorColor,
                drawableRes = R.drawable.multiply,
                drawableColor = if (selectedOperator is Operator.Multiply) selectedOperatorIconColor else operatorIconColor,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.ArithmeticOperator(Operator.Multiply)
                    onIntent(intent)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = "4",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number("4")
                    onIntent(intent)
                }
            )

            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = "5",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number("5")
                    onIntent(intent)
                }
            )

            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = "6",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number("6")
                    onIntent(intent)
                }
            )

            CalculatorIconButton(
                buttonColor = if (selectedOperator is Operator.Minus) selectedOperatorColor else operatorColor,
                drawableRes = R.drawable.minus,
                drawableColor = if (selectedOperator is Operator.Minus) selectedOperatorIconColor else operatorIconColor,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.ArithmeticOperator(Operator.Minus)
                    onIntent(intent)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = "1",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number("1")
                    onIntent(intent)
                }
            )

            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = "2",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number("2")
                    onIntent(intent)
                }
            )

            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = "3",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number("3")
                    onIntent(intent)
                }
            )

            CalculatorIconButton(
                buttonColor = if (selectedOperator is Operator.Plus) selectedOperatorColor else operatorColor,
                drawableRes = R.drawable.plus,
                drawableColor = if (selectedOperator is Operator.Plus) selectedOperatorIconColor else operatorIconColor,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.ArithmeticOperator(Operator.Plus)
                    onIntent(intent)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = "0",
                textColor = MaterialTheme.colorScheme.onTertiary,
                textPadding = PaddingValues(start = 30.dp),
                textAlign = Alignment.CenterStart,
                modifier = Modifier
                    .width(buttonWidth * 2 + buttonSpacing)
                    .height(buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number("0")
                    onIntent(intent)
                }
            )

            CalculatorTextButton(
                buttonColor = MaterialTheme.colorScheme.tertiary,
                text = ".",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Number(".")
                    onIntent(intent)
                }
            )

            CalculatorIconButton(
                buttonColor = MaterialTheme.colorScheme.primary,
                drawableRes = R.drawable.equals,
                drawableColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.Evaluate
                    onIntent(intent)
                }
            )
        }
    }
}