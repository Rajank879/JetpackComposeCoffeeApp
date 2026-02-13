package com.rajan.CoffeeShop.domain.usecase

import com.rajan.CoffeeShop.common.utils.NetworkResult
import com.rajan.CoffeeShop.data.remote.model.LoginResponse
import com.rajan.CoffeeShop.data.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        username: String,
        password: String
    ):
            NetworkResult<LoginResponse> {
        // 🔥 Business Rule 1, not allowed mailinator.com
        if (username.endsWith("@mailinator.com")) {
            return NetworkResult.Error("Mailinator account not allowed")
        }

        // 🔥 Business Rule 2, Password must be greater than 6
        if (password.length <= 6) {
            return NetworkResult.Error("Password too week")
        }

        return authRepository.login(username, password)

    }
}
