package com.rajan.CoffeeShop.data.remote

import com.rajan.CoffeeShop.data.remote.model.LoginRequest
import com.rajan.CoffeeShop.data.remote.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}