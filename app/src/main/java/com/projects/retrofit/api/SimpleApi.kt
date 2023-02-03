package com.projects.retrofit.api

import com.projects.retrofit.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SimpleApi {

    @GET("posts")
    suspend fun getPosts(): List<Post>


}