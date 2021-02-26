package com.ludovic.vimont.composeplayground

import com.ludovic.vimont.composeplayground.model.Cat
import com.ludovic.vimont.composeplayground.network.CatWebService

class MainRepository {
    suspend fun getCatData(): Cat {
        return CatWebService.getCat()
    }
}