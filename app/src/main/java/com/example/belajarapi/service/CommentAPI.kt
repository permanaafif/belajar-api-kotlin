package com.example.belajarapi.service

import com.example.belajarapi.model.Comment
import retrofit2.Call
import retrofit2.http.GET

interface CommentAPI {
    @GET("comments")
    fun getComments(): Call<List<Comment>>
}