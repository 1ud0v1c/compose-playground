package com.ludovic.vimont.composeplayground.model

data class Cat(private val image: String,
               private val facts: List<CatFacts>)