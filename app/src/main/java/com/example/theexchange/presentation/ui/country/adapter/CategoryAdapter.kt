package com.example.theexchange.presentation.ui.country.adapter

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.theexchange.R
import com.example.theexchange.data.model.Category

class CategoryAdapter(
    private val categoryList: ArrayList<Category>,
    val mContext: Context,
    val onClickCountryListener: OnClickCountryListener
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_category_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(category: Category) {
            val titleCategory = itemView.findViewById(R.id.title_category_country) as TextView
            val bannerCategory = itemView.findViewById(R.id.banner_category_country) as AppCompatImageView

            titleCategory.text = category.name

            itemView.setOnClickListener {
                onClickCountryListener.onClick(category.id)
            }

            Glide
                .with(mContext)
                .load(category.background_image)
                .centerCrop()
                .placeholder(R.drawable.ic_canada)
                .into(bannerCategory)

        }
    }

    interface OnClickCountryListener {

        fun onClick(id: Int)
    }
}