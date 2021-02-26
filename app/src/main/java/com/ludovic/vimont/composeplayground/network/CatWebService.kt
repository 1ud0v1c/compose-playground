package com.ludovic.vimont.composeplayground.network

import com.ludovic.vimont.composeplayground.model.Cat
import com.ludovic.vimont.composeplayground.util.RetrofitBuilder

object CatWebService {
    private val randomCatAPI: RandomCatAPI =
        RetrofitBuilder.buildAPI(RandomCatAPI.BASE_URL, RandomCatAPI::class.java)
    private val catFactsAPI: CatFactsAPI =
        RetrofitBuilder.buildAPI(CatFactsAPI.BASE_URL, CatFactsAPI::class.java)

    suspend fun getCat(): Cat {
        val image = randomCatAPI.getRandomCatImage().body()?.file ?: ""
        val facts = catFactsAPI.getFacts().body() ?: ArrayList()
        return Cat(image, facts)
    }
}