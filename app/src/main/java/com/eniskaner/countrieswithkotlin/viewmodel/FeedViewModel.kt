package com.eniskaner.countrieswithkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eniskaner.countrieswithkotlin.model.Country

class FeedViewModel: ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","www.ss.com")
        val country2 = Country("Italy","Europe","Rome","EUR","Italian","www.ss.com")
        val country3 = Country("Spain","Europe","Madrid","EUR","Spanish","www.ss.com")

        val countryList = arrayListOf<Country>(country,country2,country3)

        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }
}