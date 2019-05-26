package br.com.theexchange.presentation.ui.exchange_category.adapter

import android.content.Context
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import br.com.theexchange.R
import br.com.theexchange.presentation.ui.exchange_category.model.ExchangeCategoryDTO

class ExchangeCategoryAdapter(
    private val categoryList: ArrayList<ExchangeCategoryDTO>,
    val mContext: Context,
    val onClickCountryListener: OnClickCountryListener
) :
    RecyclerView.Adapter<ExchangeCategoryAdapter.ViewHolder>() {

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

        fun bindItems(exchangeCategoryDTO: ExchangeCategoryDTO) {
            val titleCategory = itemView.findViewById(R.id.title_category_country) as TextView
            val bannerCategory = itemView.findViewById(R.id.banner_category_country) as AppCompatImageView

            titleCategory.text = exchangeCategoryDTO.name

            itemView.setOnClickListener {
                onClickCountryListener.onClick(exchangeCategoryDTO.name)
            }

            val circularProgressDrawable = CircularProgressDrawable(mContext)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            Glide
                .with(mContext)
                .load(exchangeCategoryDTO.backgroundImage)
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .into(bannerCategory)

        }
    }

    interface OnClickCountryListener {
        fun onClick(name:String)
    }
}