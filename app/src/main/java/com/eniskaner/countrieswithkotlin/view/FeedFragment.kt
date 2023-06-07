package com.eniskaner.countrieswithkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.countrieswithkotlin.R
import com.eniskaner.countrieswithkotlin.adapter.CountryAdapter
import com.eniskaner.countrieswithkotlin.base.BaseFragment
import com.eniskaner.countrieswithkotlin.databinding.FragmentFeedBinding
import com.eniskaner.countrieswithkotlin.viewmodel.FeedViewModel

class FeedFragment : BaseFragment<FragmentFeedBinding>() {

    override fun setBinding():FragmentFeedBinding = FragmentFeedBinding.inflate(layoutInflater)

    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.apply {
            countryList.layoutManager = LinearLayoutManager(context)
            countryList.adapter = countryAdapter
        }

        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                countryList.visibility = View.GONE
                countryError.visibility = View.GONE
                countryLoading.visibility = View.VISIBLE
                viewModel.refreshData()
                swipeRefreshLayout.isRefreshing = false
            }
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->
            countries?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                binding.apply {
                    if (it) {
                        countryError.visibility = View.VISIBLE
                    } else {
                        countryError.visibility = View.GONE
                    }
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                binding.apply {
                    if (it) {
                        countryLoading.visibility = View.VISIBLE
                        countryList.visibility = View.GONE
                        countryError.visibility = View.GONE
                    } else {
                        countryLoading.visibility = View.GONE
                    }
                }
            }
        })
    }

}