package com.example.theexchange.presentation.ui.main.fragment.adapter

import android.content.Context
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.theexchange.R
import com.example.theexchange.presentation.ui.main.model.CountryDTO

class CountriesAdapter(
    val countriesList: ArrayList<CountryDTO>,
    val mContext: Context,
    val onClickCountryListener: OnClickCountryListener
) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CountriesAdapter.ViewHolder, position: Int) {
        holder.bindItems(countriesList[position])
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(countryDTO: CountryDTO) {
            val textViewName = itemView.findViewById(R.id.textViewCountryName) as TextView
            val textViewDescription = itemView.findViewById(R.id.textViewCountryShortDescription) as TextView
            val imageViewCountry = itemView.findViewById(R.id.circleImageViewCountry) as ImageView
            textViewName.text = countryDTO.name
            textViewDescription.text = countryDTO.short_description

            itemView.setOnClickListener {
                onClickCountryListener.onClick(countryDTO.id,countryDTO.name)
            }

            val circularProgressDrawable = CircularProgressDrawable(mContext)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide
                .with(mContext)
                .load(countryDTO.flag_image)
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .into(imageViewCountry)

        }
    }

    interface OnClickCountryListener {

        fun onClick(id: Int,name:String)
    }
}