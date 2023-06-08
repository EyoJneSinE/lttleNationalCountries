package com.eniskaner.countrieswithkotlin.adapter

import android.view.View
import com.eniskaner.countrieswithkotlin.databinding.ItemCountryBinding

interface CountryClickListener {
    fun onCountryClicked(view: View)
}