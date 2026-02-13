package com.rajan.CoffeeShop.data.repository

import com.rajan.CoffeeShop.common.utils.NetworkResult
import com.rajan.CoffeeShop.data.remote.model.LoginResponse

interface AuthRepository {
    suspend fun login(username: String, password: String): NetworkResult<LoginResponse>
}