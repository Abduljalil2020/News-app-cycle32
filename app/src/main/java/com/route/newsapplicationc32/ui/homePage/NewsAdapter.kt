package com.route.newsapplicationc32.ui.homePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route.newsapplicationc32.R
import com.route.newsapplicationc32.api.model.ArticlesItem

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var data = listOf<ArticlesItem?>()

    fun changeData(list: List<ArticlesItem?>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = data[position]
        holder.title.text = news?.title
        holder.source.text = news?.source?.name
        holder.date.text = news?.publishedAt
        holder.desc.text = news?.description

        Glide.with(holder.itemView)
            .load(news?.urlToImage)
            .into(holder.image)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var desc: TextView = itemView.findViewById(R.id.description)
        var date: TextView = itemView.findViewById(R.id.date)
        var source: TextView = itemView.findViewById(R.id.source)
        var image: ImageView = itemView.findViewById(R.id.image)

    }
}