package com.rajan.CoffeeShop.data.remote.model

data class SignUpRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
