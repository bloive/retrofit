package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitService {

    private const val BASE_URL = "https://restcountries.eu/"

    // Parse manually
//    fun retrofitService(): RetrofitRepository {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .build()
//            .create(RetrofitRepository::class.java)
//    }

        fun retrofitService(): RetrofitRepository {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitRepository::class.java)
    }
}