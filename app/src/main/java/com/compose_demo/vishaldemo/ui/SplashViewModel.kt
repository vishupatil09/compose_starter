package com.compose_demo.vishaldemo.ui

import androidx.lifecycle.ViewModel
import com.compose_demo.vishaldemo.ui.navigation.Navigator
import com.compose_demo.vishaldemo.utils.Shareprefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import staff.management.system.repository.NetworkRepository
import javax.inject.Inject


@HiltViewModel
class SplashViewModel
@Inject
constructor(
    private val navigator: Navigator,
    private val networkRepository: NetworkRepository,
    private val sharePrefs: Shareprefs
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState

    val splashInterActor = object : SplashInterActor {
        override fun navigateToNext() {

        }

    }
}