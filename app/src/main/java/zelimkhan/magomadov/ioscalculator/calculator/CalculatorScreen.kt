package zelimkhan.magomadov.ioscalculator.calculator

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zelimkhan.magomadov.ioscalculator.R
import zelimkhan.magomadov.ioscalculator.components.auto_size_text.AutoSizeText
import zelimkhan.magomadov.ioscalculator.ui.theme.IOSCalculatorTheme

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    state: CalculatorState = CalculatorState(),
    onIntent: (CalculatorIntent) -> Unit = {}
) {
    val buttonSpacing = 12.dp

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        AutoSizeText(
            text = state.currentInput,
            maxTextSize = 85.sp,
            minTextSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.aileron_thin)),
            fontWeight = FontWeight.ExtraLight,
            alignment = Alignment.BottomEnd,
            maxLines = 1,
            style = TextStyle(
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    LineHeightStyle.Trim.Both
                )
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = buttonSpacing * 2),
        )

        BoxWithConstraints {
            val availableWidth = maxWidth - buttonSpacing * 5
            val availableHeight = maxHeight - buttonSpacing * 6

            val maxButtonWidth = availableWidth / 4
            val maxButtonHeight = availableHeight / 5

            val buttonSize = when {
                maxButtonHeight >= maxButtonWidth -> maxButtonWidth
                else -> maxButtonHeight
            }

            val buttonWidth = when {
                maxButtonHeight >= maxButtonWidth -> buttonSize
                else -> maxButtonWidth
            }

            val buttonHeight = when {
                maxButtonHeight >= maxButtonWidth -> buttonSize
                else -> maxButtonHeight
            }

            CalculatorButtonSection(
                buttonSpacing = buttonSpacing,
                buttonWidth = buttonWidth,
                buttonHeight = buttonHeight,
                selectedOperator = state.currentOperator,
                onIntent = onIntent
            )
        }
    }
}

@Composable
private fun CalculatorButtonSection(
    buttonSpacing: Dp,
    buttonWidth: Dp,
    buttonHeight: Dp,
    modifier: Modifier = Modifier,
    selectedOperator: Operator? = null,
    onIntent: (CalculatorIntent) -> Unit = {}
) {
    val operatorColor = MaterialTheme.colorScheme.primary
    val selectedOperatorColor = Color(0xFFFCC78D)

    Column(
        modifier = modifier.padding(buttonSpacing),
        verticalArrangement = Arrangement.spacedBy(buttonSpacing)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(
                color = MaterialTheme.colorScheme.secondary,
                symbol = "AC",
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.OperatorClick(Operator.Clean)
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = MaterialTheme.colorScheme.secondary,
                drawableRes = R.drawable.unary_plus_minus,
                drawableColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.OperatorClick(Operator.UnaryPlusMinus)
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = MaterialTheme.colorScheme.secondary,
                symbol = "%",
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.OperatorClick(Operator.Percent)
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = if (selectedOperator is Operator.Divide) selectedOperatorColor else operatorColor,
                drawableRes = R.drawable.divide,
                drawableColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.OperatorClick(Operator.Divide)
                    onIntent(intent)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = "7",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDigit('7')
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = "8",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDigit('8')
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = "9",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDigit('9')
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = if (selectedOperator is Operator.Multiply) selectedOperatorColor else operatorColor,
                drawableRes = R.drawable.multiply,
                drawableColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.OperatorClick(Operator.Multiply)
                    onIntent(intent)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = "4",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDigit('4')
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = "5",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDigit('5')
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = "6",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDigit('6')
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = if (selectedOperator is Operator.Minus) selectedOperatorColor else operatorColor,
                drawableRes = R.drawable.minus,
                drawableColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.OperatorClick(Operator.Minus)
                    onIntent(intent)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = "1",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDigit('1')
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = "2",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDigit('2')
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = "3",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDigit('3')
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = if (selectedOperator is Operator.Plus) selectedOperatorColor else operatorColor,
                drawableRes = R.drawable.plus,
                drawableColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.OperatorClick(Operator.Plus)
                    onIntent(intent)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = "0",
                textColor = MaterialTheme.colorScheme.onTertiary,
                textPadding = PaddingValues(start = 30.dp),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .width(buttonWidth * 2 + buttonSpacing)
                    .height(buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDigit('0')
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = MaterialTheme.colorScheme.tertiary,
                symbol = ".",
                textColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.EnterDot
                    onIntent(intent)
                }
            )

            CalculatorButton(
                color = MaterialTheme.colorScheme.primary,
                drawableRes = R.drawable.equals,
                drawableColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(buttonWidth, buttonHeight),
                onClick = {
                    val intent = CalculatorIntent.OperatorClick(Operator.Equals)
                    onIntent(intent)
                }
            )
        }
    }
}

@Composable
fun CalculatorButton(
    color: Color,
    modifier: Modifier = Modifier,
    symbol: String? = null,
    @DrawableRes drawableRes: Int? = null,
    drawableColor: Color = MaterialTheme.colorScheme.onTertiary,
    fontFamily: FontFamily = FontFamily(Font(R.font.inter_regular)),
    fontSize: TextUnit = 35.sp,
    textColor: Color = MaterialTheme.colorScheme.onTertiary,
    textAlign: TextAlign = TextAlign.Center,
    textPadding: PaddingValues = PaddingValues(),
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                color = color,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (drawableRes != null) {
            Image(
                painter = painterResource(id = drawableRes),
                contentDescription = null,
                colorFilter = ColorFilter.tint(drawableColor)
            )
        } else {
            AutoSizeText(
                text = symbol ?: "",
                minTextSize = 0.sp,
                maxTextSize = fontSize,
                fontFamily = fontFamily,
                alignment = Alignment.Center,
                color = textColor,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(textPadding)
            )
        }
    }
}

@Preview(
    heightDp = 400
)
@Composable
private fun Preview() {
    IOSCalculatorTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CalculatorScreen()
        }
    }
}