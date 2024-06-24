package zelimkhan.magomadov.ioscalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import zelimkhan.magomadov.ioscalculator.calculator.CalculatorScreen
import zelimkhan.magomadov.ioscalculator.calculator.CalculatorViewModel
import zelimkhan.magomadov.ioscalculator.sl.ServiceLocator
import zelimkhan.magomadov.ioscalculator.ui.theme.IOSCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IOSCalculatorTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: CalculatorViewModel by viewModels<CalculatorViewModel> {
                        ServiceLocator.provideCalculatorViewModelFactory()
                    }

                    val state by viewModel.state.collectAsState()
                    CalculatorScreen(
                        modifier = Modifier.systemBarsPadding(),
                        state = state,
                        onIntent = viewModel::processIntent
                    )
                }
            }
        }
    }
}