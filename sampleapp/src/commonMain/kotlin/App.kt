import androidx.compose.runtime.Composable
import com.thondigital.sampleapp.presentation.NCTheme
import com.thondigital.sampleapp.presentation.ui.home.HomeScreen

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    NCTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        HomeScreen()
    }
}
