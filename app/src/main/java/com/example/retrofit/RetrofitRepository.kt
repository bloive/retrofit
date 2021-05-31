package com.example.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitRepository {
    @GET("/rest/v2/all")
    suspend fun getCountry(): Response<List<ItemModel>>
}