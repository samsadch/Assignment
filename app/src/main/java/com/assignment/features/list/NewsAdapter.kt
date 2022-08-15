package com.assignment.features.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.data.db.NewsData
import com.assignment.databinding.ListItemNewsBinding
import com.bumptech.glide.Glide

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 15/08/2022
 */
class NewsAdapter : ListAdapter<NewsData, NewsAdapter.ViewHolder>(DiffCallBack()) {


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

        fun bind(item: NewsData) {
            binding.apply {
                headlineText.text = item.title
                byText.text = item.byline
                dateTxv.text = item.published_date
                sourceText.text = item.source

                //item.media[0].mediaMetadata[1].url
                Glide.with(binding.root.context).load(item.thumbnail)
                    .placeholder(R.drawable.grey_round).into(thumbImage)
                //setThumbImage(item,holder)
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<NewsData>() {
        override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData) =
            newItem.id == oldItem.id

        override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData) = oldItem == newItem

    }

}
