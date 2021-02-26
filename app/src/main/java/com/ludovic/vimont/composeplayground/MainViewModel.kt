package com.ludovic.vimont.composeplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ludovic.vimont.composeplayground.model.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val mainRepository = MainRepository()
    private val mutableCatData = MutableLiveData<Cat>()
    val cat: LiveData<Cat> = mutableCatData

    fun loadCat() {
        GlobalScope.launch(Dispatchers.Default) {
            val fetchedCat = mainRepository.getCatData()
            mutableCatData.postValue(fetchedCat)
        }
    }
}