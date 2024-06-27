package zelimkhan.magomadov.ioscalculator.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import zelimkhan.magomadov.ioscalculator.core.components.auto_size_text.AutoSizeText
import zelimkhan.magomadov.ioscalculator.ui.theme.IOSCalculatorFontFamily

@Composable
fun CalculatorTextButton(
    text: String,
    modifier: Modifier = Modifier,
    buttonColor: Color = MaterialTheme.colorScheme.tertiary,
    fontFamily: FontFamily = IOSCalculatorFontFamily.Inter,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 35.sp,
    textColor: Color = MaterialTheme.colorScheme.onTertiary,
    textAlign: Alignment = Alignment.Center,
    textPadding: PaddingValues = PaddingValues(),
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                color = buttonColor,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        AutoSizeText(
            text = text,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            maxTextSize = fontSize,
            minTextSize = 0.sp,
            alignment = textAlign,
            color = textColor,
            modifier = Modifier
                .fillMaxSize()
                .padding(textPadding)
        )
    }
}