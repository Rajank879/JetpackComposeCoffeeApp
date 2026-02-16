package com.rajan.CoffeeShop.domain.usecase

import com.rajan.CoffeeShop.common.utils.NetworkResult
import com.rajan.CoffeeShop.data.remote.model.LoginResponse
import com.rajan.CoffeeShop.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): NetworkResult<LoginResponse> {
        if (email.isBlank()) {
            return NetworkResult.Error("Email is required")
        }

        if (password.isBlank()) {
            return NetworkResult.Error("Password is required")
        }

        if (password.length < 6) {
            return NetworkResult.Error("Password should be greater than 6 characters")
        }

//        if (!email.contains("@")) {
//            return NetworkResult.Error("Invalid Email")
//        }
//
//        if (!email.contains(".")) {
//            return NetworkResult.Error("Invalid Email")
//        }

        return authRepository.login(email, password)
    }
}
