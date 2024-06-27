package zelimkhan.magomadov.ioscalculator.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zelimkhan.magomadov.ioscalculator.calculator.components.CalculatorButtonSection
import zelimkhan.magomadov.ioscalculator.calculator.state.CalculatorIntent
import zelimkhan.magomadov.ioscalculator.calculator.state.CalculatorState
import zelimkhan.magomadov.ioscalculator.core.components.auto_size_text.AutoSizeText
import zelimkhan.magomadov.ioscalculator.ui.theme.IOSCalculatorFontFamily
import zelimkhan.magomadov.ioscalculator.ui.theme.IOSCalculatorTheme

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    state: CalculatorState = CalculatorState(),
    onIntent: (CalculatorIntent) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        val buttonSpacing = 12.dp

        AutoSizeText(
            text = state.currentInput,
            maxTextSize = 90.sp,
            minTextSize = 24.sp,
            fontFamily = IOSCalculatorFontFamily.Aileron,
            fontWeight = FontWeight.Thin,
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
                .height(110.dp)
                .padding(horizontal = buttonSpacing * 2)
        )

        BoxWithConstraints {
            val (buttonWidth, buttonHeight) = remember(maxWidth, maxHeight) {
                calculateButtonSize(maxWidth, maxHeight, buttonSpacing)
            }

            CalculatorButtonSection(
                buttonSpacing = buttonSpacing,
                buttonWidth = buttonWidth,
                buttonHeight = buttonHeight,
                selectedOperator = state.currentOperator,
                clearState = state.clearState,
                onIntent = onIntent
            )
        }
    }
}

private fun calculateButtonSize(
    maxWidth: Dp,
    maxHeight: Dp,
    buttonSpacing: Dp
): Pair<Dp, Dp> {
    val buttonsInRow = 4
    val buttonsInColumn = 5

    val availableWidth = maxWidth - buttonSpacing * (buttonsInRow + 1)
    val availableHeight = maxHeight - buttonSpacing * (buttonsInColumn + 1)

    val maxButtonWidth = availableWidth / buttonsInRow
    val maxButtonHeight = availableHeight / buttonsInColumn

    return when {
        maxButtonHeight >= maxButtonWidth -> Pair(maxButtonWidth, maxButtonWidth)
        else -> Pair(maxButtonWidth, maxButtonHeight)
    }
}

@Preview
@Composable
private fun Preview() {
    IOSCalculatorTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CalculatorScreen(state = CalculatorState(currentInput = "1"))
        }
    }
}