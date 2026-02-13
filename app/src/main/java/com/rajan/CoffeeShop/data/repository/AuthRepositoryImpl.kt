package com.rajan.CoffeeShop.data.repository

import com.google.gson.Gson
import com.rajan.CoffeeShop.common.utils.NetworkResult
import com.rajan.CoffeeShop.data.remote.AuthApi
import com.rajan.CoffeeShop.data.remote.model.ErrorResponse
import com.rajan.CoffeeShop.data.remote.model.LoginRequest
import com.rajan.CoffeeShop.data.remote.model.LoginResponse
import org.json.JSONObject
import javax.inject.Inject

class AuthRepositoryImpl@Inject constructor(
    private val api: AuthApi
) : AuthRepository {
    override suspend fun login(username: String, password: String): NetworkResult<LoginResponse> {
        return try {
            val response =  api.login(LoginRequest(username,password))
           if(response.isSuccessful && response.body()!=null){
               response.body()?.let {
                   NetworkResult.Success(response.body()!!)
               }?: NetworkResult.Error("Something went wrong")
           }else{
               val errorBody = response.errorBody()?.string()
               val message = try {
                   JSONObject(errorBody ?: "{}")
                       .optString("message", "Something went wrong")
               } catch (e: Exception) {
                   "Something went wrong"
               }

               NetworkResult.Error(message)
           }
        } catch (e: Exception){
            NetworkResult.Error(e.message?: "Network Error")
        }

    }
}