package com.rajan.CoffeeShop.data.remote.model

data class LoginResponse(
    val id: Int,
    val username: String,
    val email: String,
    val token: String
)