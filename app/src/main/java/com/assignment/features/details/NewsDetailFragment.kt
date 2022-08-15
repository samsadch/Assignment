package com.assignment.features.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.assignment.R
import com.assignment.data.db.NewsData
import com.assignment.databinding.FragmentNewsDetailsBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 15/08/2022
 */
@AndroidEntryPoint
class NewsDetailFragment : Fragment() {
    private val viewModel: NewsDetailViewModel by viewModels()
    lateinit var binding: FragmentNewsDetailsBinding
    private val args: NewsDetailFragmentArgs by navArgs()
    private var newsId: Long? = null
    private lateinit var news: NewsData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAndLoadNews()
        initViews()
    }

    private fun getAndLoadNews() {
        newsId = args.newsId
        viewModel.getNewsById(newsId ?: 0)
    }

    private fun initViews() {
        binding.apply {
            icShare.setOnClickListener {
                openNews()
            }
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.newsDetailEvent.collect { event ->
                    when (event) {
                        is NewsDetailViewModel.NewsDetailEvent.OnNewsAvailable -> {
                            news = event.news
                            detailHeader.text = event.news.title
                            attText.text = event.news.abstract
                            Glide.with(requireContext()).load(event.news.thumbnail)
                                .placeholder(R.drawable.placeholder).into(detailImage)
                        }
                    }
                }
            }
        }
    }

    private fun openNews() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
        requireContext().startActivity(browserIntent)
    }
}