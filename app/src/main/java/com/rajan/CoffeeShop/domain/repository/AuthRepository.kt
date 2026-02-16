package com.rajan.CoffeeShop.domain.repository

import com.rajan.CoffeeShop.common.utils.NetworkResult
import com.rajan.CoffeeShop.data.remote.model.LoginResponse
import com.rajan.CoffeeShop.data.remote.model.SignUpRequest
import com.rajan.CoffeeShop.data.remote.model.SignupResponse

interface AuthRepository {
    suspend fun login(username: String, password: String): NetworkResult<LoginResponse>

    suspend fun signup( firstName: String, lastName: String, email: String, password: String): NetworkResult<SignupResponse>


}