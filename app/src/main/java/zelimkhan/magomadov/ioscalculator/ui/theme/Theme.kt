package zelimkhan.magomadov.ioscalculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = LightPrimaryColor,
    onPrimary = LightOnPrimaryColor,
    secondary = LightSecondaryColor,
    onSecondary = LightOnSecondaryColor,
    tertiary = LightTertiaryColor,
    onTertiary = LightOnTertiaryColor,
    background = LightBackgroundColor,
    onBackground = LightOnBackgroundColor
)

@Composable
fun IOSCalculatorTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}