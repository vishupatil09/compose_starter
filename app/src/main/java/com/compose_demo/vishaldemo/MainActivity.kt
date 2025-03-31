package com.compose_demo.vishaldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose_demo.vishaldemo.ui.navigation.NavigationCommand
import com.compose_demo.vishaldemo.ui.navigation.NavigationEvent
import com.compose_demo.vishaldemo.ui.navigation.Navigator
import com.compose_demo.vishaldemo.ui.theme.VishalDemoTheme
import staff.management.system.ui.navigation.AppScreens
import staff.management.system.ui.navigation.screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VishalDemoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    Vishal {
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun Vishal(
    navigator: Navigator = hiltViewModel(),
    finishApp: () -> Unit
) {
    val navController = rememberNavController()
    val navigationCommands by navigator.navigation.collectAsState(
        initial = NavigationEvent(NavigationCommand.Idle)

    )
    when (val command = navigationCommands.command) {
        NavigationCommand.Back -> {
            if (!navController.popBackStack()) {
                finishApp()
            }
        }

        is NavigationCommand.RouteCommand -> {
            when (command) {
                is NavigationCommand.To -> navController.navigate(command.routeWithArgs)
                is NavigationCommand.ToAndClear -> {
                    navController.navigate(command.routeWithArgs) {
                        popUpTo(
                            navController.currentBackStackEntry?.destination?.route
                                ?: return@navigate
                        ) { inclusive = true }
                    }
                }

                is NavigationCommand.ToAndClearAll -> {
                    navController.navigate(command.routeWithArgs) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }

                else -> {}
            }
        }

        NavigationCommand.Idle -> {}
    }

    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        screens.forEach { screen ->
            composable(
                route = "${screen.route}?json={json}",
                arguments = listOf(navArgument("json") {
                    type = NavType.StringType
                    defaultValue = ""
                })
            ) { backStackEntry ->
                screen.content(backStackEntry)
            }
        }
    }
}