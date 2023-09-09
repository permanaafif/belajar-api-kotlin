package com.example.belajarapi.service

import com.example.belajarapi.model.CariUserResponse
import com.example.belajarapi.model.UserRequest
import com.example.belajarapi.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {
    @POST("api/users")
    fun createUser(@Body req: UserRequest): Call <UserResponse>

    @GET("api/users/{id}")
    fun cari(@Path("id") id:Int): Call <CariUserResponse>
}