package com.example.retrofit

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel() {

    private val countriesLiveData = MutableLiveData<List<ItemModel>>().apply {
        mutableListOf<ItemModel>()
    }

    private val loadingLiveData = MutableLiveData<Boolean>().apply {
        true
    }

    val _loadingLiveData: LiveData<Boolean> = loadingLiveData

    val _countriesLiveData: LiveData<List<ItemModel>> = countriesLiveData

    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            getCountries()
        }
    }

    private suspend fun getCountries() {
        val response = RetrofitService.retrofitService().getCountry()
        if (response.isSuccessful) {
            val items = response.body()
            items?.forEach{
                d("name", "${it.name}")
            }
        } else {
            response.code()
        }
        loadingLiveData.postValue(false)
    }
}