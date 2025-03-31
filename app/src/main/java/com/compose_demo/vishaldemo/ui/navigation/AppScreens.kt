package  staff.management.system.ui.navigation


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose_demo.vishaldemo.ui.SplashScreen
import com.compose_demo.vishaldemo.ui.SplashViewModel

sealed class AppScreens(val route: String) {
    data object SplashScreen : AppScreens("SplashScreen")
}

val screens = listOf(
    Screen(AppScreens.SplashScreen.route) {
        val viewModel = hiltViewModel<SplashViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        SplashScreen( uiState = uiState , interActor = viewModel.splashInterActor)
    },

    )
