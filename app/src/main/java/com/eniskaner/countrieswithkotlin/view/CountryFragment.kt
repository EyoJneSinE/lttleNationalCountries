package com.eniskaner.countrieswithkotlin.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eniskaner.countrieswithkotlin.base.BaseFragment
import com.eniskaner.countrieswithkotlin.databinding.FragmentCountryBinding
import com.eniskaner.countrieswithkotlin.viewmodel.CountryViewModel
class CountryFragment : BaseFragment<FragmentCountryBinding>() {

    override fun setBinding(): FragmentCountryBinding = FragmentCountryBinding.inflate(layoutInflater)

    private lateinit var viewModel : CountryViewModel
    private var countryUuid = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()


        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country->
            country?.let {

                viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { countries ->
                    countries?.let {
                        binding.apply {
                            countryListName.text = country.countryName
                            countryListCapital.text = country.countryCapital
                            countryListCurrency.text = country.countryCurrency
                            countryListLanguage.text = country.countryLanguage
                            countryListRegion.text = country.countryRegion
                        }
                    }
                })

            }
        })
    }


}