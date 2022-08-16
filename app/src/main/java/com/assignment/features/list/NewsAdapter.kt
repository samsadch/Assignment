package com.assignment.features.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.api.NYApi
import com.assignment.data.db.NewsData
import com.assignment.databinding.ListItemNewsBinding
import com.assignment.utils.getDateString
import com.bumptech.glide.Glide

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 15/08/2022
 */
class NewsAdapter(val listener: NewsCallBack) :
    ListAdapter<NewsData, NewsAdapter.ViewHolder>(DiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class ViewHolder(private val binding: ListItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        listener.onNewsClick(getItem(adapterPosition))
                    }
                }
            }
        }

        fun bind(item: NewsData) {
            binding.apply {
                headlineText.text = item.title
                byText.text = item.byline
                dateTxv.text = getDateString(item.published_date!!.time, NYApi.PUBLISH_DATE_FORMAT)
                sourceText.text = item.source

                Glide.with(binding.root.context).load(item.thumbnail)
                    .placeholder(R.drawable.grey_round).into(thumbImage)
            }
        }
    }

    interface NewsCallBack {
        fun onNewsClick(news: NewsData) {

        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<NewsData>() {
    override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData) =
        newItem.id == oldItem.id

    override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData) = oldItem == newItem

}
