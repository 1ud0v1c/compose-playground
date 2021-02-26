package com.ludovic.vimont.composeplayground.network

import com.ludovic.vimont.composeplayground.model.Cat
import com.ludovic.vimont.composeplayground.model.CatFacts
import com.ludovic.vimont.composeplayground.util.RetrofitBuilder
import java.util.*
import kotlin.collections.ArrayList

object CatWebService {
    private val randomCatAPI: RandomCatAPI =
        RetrofitBuilder.buildAPI(RandomCatAPI.BASE_URL, RandomCatAPI::class.java)
    private val catFactsAPI: CatFactsAPI =
        RetrofitBuilder.buildAPI(CatFactsAPI.BASE_URL, CatFactsAPI::class.java)

    fun defaultCat(): Cat {
        val image = "https://picsum.photos/seed/picsum/200/300"
        val facts = listOf(
            CatFacts("Test", Date().time.toString()),
            CatFacts("Lorem Ipsum", Date().time.toString()),
            CatFacts("I love cats", Date().time.toString()),
            CatFacts("Very much", Date().time.toString())
        )
        return Cat(image, facts)
    }

    suspend fun getCat(): Cat {
        val image = randomCatAPI.getRandomCatImage().body()?.file ?: ""
        val facts = catFactsAPI.getFacts().body() ?: ArrayList()
        return Cat(image, facts)
    }
}