package com.projects.retrofit.`object`

import com.projects.retrofit.api.SimpleApi
import com.projects.retrofit.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// singleton pattern is used as a replacement for static members and fields that don't exist in that programming language
// declaring an object . Contrary to a class , an object can't have any constructor
object RetrofitInstance {
    // we cannot be sure that the object, for which we paid the cost of initialization at the start of our program, will be used in our program at all
    // lazy initialization’ was designed to prevent unnecessary initialization of objects
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }


}