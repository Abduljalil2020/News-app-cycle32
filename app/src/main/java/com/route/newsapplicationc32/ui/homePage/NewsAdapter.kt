package com.route.newsapplicationc32.ui.homePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.newsapplicationc32.R
import com.route.newsapplicationc32.api.model.ArticlesItem
import com.route.newsapplicationc32.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var data = listOf<ArticlesItem?>()

    fun changeData(list: List<ArticlesItem?>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val databinding = DataBindingUtil.inflate<ItemNewsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_news, parent, false
        )
        return ViewHolder(databinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = data[position]!!
        holder.bind(news)
    }

    class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: ArticlesItem) {
            binding.news = news
            binding.invalidateAll()
        }

    }
}