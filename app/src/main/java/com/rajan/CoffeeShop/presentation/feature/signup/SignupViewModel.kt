package com.rajan.CoffeeShop.presentation.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajan.CoffeeShop.common.utils.NetworkResult
import com.rajan.CoffeeShop.domain.usecase.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnFirstnameChanged -> {
                _uiState.value = uiState.value.copy(
                    firstName = event.firstname,
                    error = null
                )
            }

            is SignUpEvent.OnLastnameChanged -> {
                _uiState.value = uiState.value.copy(
                    lastName = event.lastname,
                    error = null
                )
            }

            is SignUpEvent.OnEmailChanged -> {
                _uiState.value = uiState.value.copy(
                    email = event.email,
                    error = null
                )
            }

            is SignUpEvent.OnPasswordChanged -> {
                _uiState.value = uiState.value.copy(
                    password = event.newPassword,
                    error = null
                )
            }

            is SignUpEvent.OnConfirmPasswordChanged -> {
                _uiState.value = uiState.value.copy(
                    confirmPassword = event.newConfirmPassword,
                    error = null
                )
            }

            is SignUpEvent.OnSignUpClicked -> validateSignUp()
            is SignUpEvent.ClearError -> {
                _uiState.value = uiState.value.copy(
                    error = null
                )
            }
            is SignUpEvent.OnLoginClicked -> {}

        }
    }

    private fun validateSignUp() {
        val state = _uiState.value
        if (state.password != state.confirmPassword){
            _uiState.value = state.copy(error = "Password and confirm password do not match")
            return
        }

        viewModelScope.launch {
            _uiState.value = state.copy(isLoading = true, error = null)
            when(val result = signupUseCase(firstName = state.firstName,
                lastName = state.lastName,
                email = state.email,
                password = state.password)){
                is NetworkResult.Success->{
                    _uiState.value = state.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                }
                is NetworkResult.Error->{
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is NetworkResult.Loading -> {
                    _uiState.value = state.copy(
                        isLoading = true,
                        isSuccess = false
                    )
                }
                else -> Unit
            }

        }

    }
}