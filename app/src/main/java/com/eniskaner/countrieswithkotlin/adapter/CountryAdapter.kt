package com.eniskaner.countrieswithkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.countrieswithkotlin.databinding.ItemCountryBinding
import com.eniskaner.countrieswithkotlin.model.Country
import com.eniskaner.countrieswithkotlin.util.downloadFromUrl
import com.eniskaner.countrieswithkotlin.util.placeholderProgressBar
import com.eniskaner.countrieswithkotlin.view.FeedFragmentDirections
import dagger.hilt.android.qualifiers.ApplicationContext

class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(val binding: ItemCountryBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context))
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.apply {
            countryName.text = countryList[position].countryName
            countryRegion.text = countryList[position].countryRegion

            holder.itemView.setOnClickListener {
                val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
                Navigation.findNavController(it).navigate(action)
            }
            countryImage.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.itemView.context))
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}