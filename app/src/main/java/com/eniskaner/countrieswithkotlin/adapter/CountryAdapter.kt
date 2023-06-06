package com.eniskaner.countrieswithkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.countrieswithkotlin.R
import com.eniskaner.countrieswithkotlin.databinding.ItemCountryBinding
import com.eniskaner.countrieswithkotlin.model.Country
import com.eniskaner.countrieswithkotlin.view.FeedFragmentDirections
class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(val binding: ItemCountryBinding): RecyclerView.ViewHolder(binding.root) {

    }
    private val differCallback = object : DiffUtil.ItemCallback<Country>(){
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context))
        return CountryViewHolder(binding)
    }

    private var onItemClickListener:((Country) -> Unit)? = null

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = differ.currentList[position]
        holder.binding.countryName.text = countryList[position].countryName
        holder.binding.countryRegion.text = countryList[position].countryRegion

        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let {
                    it(country)
                }
            }
        }

    }

    fun setOnItemClickListener(listener: (Country) -> Unit) {
        onItemClickListener = listener
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