package com.ludovic.vimont.composeplayground.network

import com.ludovic.vimont.composeplayground.model.RandomCat
import retrofit2.Response
import retrofit2.http.GET

interface RandomCatAPI {
    companion object {
        const val BASE_URL = "https://aws.random.cat/"
    }

    @GET("meow")
    suspend fun getRandomCatImage(): Response<RandomCat>
}