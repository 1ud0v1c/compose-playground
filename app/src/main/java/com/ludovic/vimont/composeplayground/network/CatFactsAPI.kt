package com.ludovic.vimont.composeplayground.network

import com.ludovic.vimont.composeplayground.model.CatFacts
import retrofit2.Response
import retrofit2.http.GET

interface CatFactsAPI {
    companion object {
        const val BASE_URL = "https://cat-fact.herokuapp.com/"
    }

    @GET("facts")
    suspend fun getFacts(): Response<List<CatFacts>>
}