package com.rajan.CoffeeShop.presentation.screen.loginscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajan.CoffeeShop.common.utils.NetworkResult
import com.rajan.CoffeeShop.data.remote.model.LoginResponse
import com.rajan.CoffeeShop.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel
@Inject constructor(private val loginUseCase: LoginUseCase) :
    ViewModel() {

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Idle)
    val uiState: StateFlow<NetworkResult<LoginResponse>> = _uiState

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun onLoginClick() {

        // UI validation error
        if (email.isBlank()) {
            _uiState.value = NetworkResult.Error("Email cannot be empty")
            return
        }

        if (password.isBlank()) {
            _uiState.value = NetworkResult.Error("Password cannot be empty")
            return
        }

        viewModelScope.launch {
            _uiState.value = NetworkResult.Loading
            val result = loginUseCase(email, password)
            _uiState.value = result

        }
    }
}