package com.ludovic.vimont.composeplayground.network

import com.ludovic.vimont.composeplayground.model.Cat
import com.ludovic.vimont.composeplayground.util.RetrofitBuilder

object CatWebService {
    private val randomCatAPI: RandomCatAPI
    private val catFactsAPI: CatFactsAPI

    init {
        randomCatAPI = RetrofitBuilder.buildRetrofitForAPI(RandomCatAPI.BASE_URL, RandomCatAPI::class.java)
        catFactsAPI = RetrofitBuilder.buildRetrofitForAPI(CatFactsAPI.BASE_URL, CatFactsAPI::class.java)
    }

    suspend fun getCat(): Cat {
        val randomCatImage = randomCatAPI.getRandomCatImage().body()?.file ?: ""
        val catFact = catFactsAPI.getFacts().body()?.random()?.text ?: ""
        return Cat(randomCatImage, catFact)
    }
}