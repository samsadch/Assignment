package com.assignment.features.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.R
import com.assignment.data.db.NewsData
import com.assignment.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
@AndroidEntryPoint
class NewsListFragment : Fragment(R.layout.fragment_news_list), SearchView.OnQueryTextListener,
    NewsAdapter.NewsCallBack {

    private val viewModel: NewsListViewModel by viewModels()
    lateinit var binding: FragmentNewsListBinding
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsAdapter(this)
        binding.apply {
            newsRcv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = newsAdapter
            }
        }
        setHasOptionsMenu(true)
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.newsEvent.collect { event ->
                when (event) {
                    is NewsListViewModel.NewsEvent.ShowMessage -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }
                    is NewsListViewModel.NewsEvent.ShowProgress -> {
                        binding.progress.isVisible = event.message
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.news.collect { list ->
                if (list != null) {
                    binding.retryButton.isVisible = false
                    binding.newsRcv.isVisible = list.isNotEmpty()
                    binding.noItemText.isVisible = list.isEmpty()
                    newsAdapter.submitList(list)
                } else {
                    binding.newsRcv.isVisible = true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {

                true
            }
            R.id.filter_day -> {
                viewModel.getNews(1)
                true
            }
            R.id.filter_month -> {
                viewModel.getNews(30)
                true
            }
            R.id.filter_week -> {
                viewModel.getNews(7)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.setQuery(query.toString())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.setQuery(newText.toString())
        return true
    }


    override fun onNewsClick(news: NewsData) {
        super.onNewsClick(news)
        val action = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(news)
        findNavController().navigate(action)
    }

}