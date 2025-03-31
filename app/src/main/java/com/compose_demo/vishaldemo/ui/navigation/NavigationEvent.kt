package  com.compose_demo.vishaldemo.ui.navigation

import com.compose_demo.vishaldemo.ui.navigation.NavigationCommand


data class NavigationEvent(
    val command: NavigationCommand,
    val id: Long = System.currentTimeMillis()
)
