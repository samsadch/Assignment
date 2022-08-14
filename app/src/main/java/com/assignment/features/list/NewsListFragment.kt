package com.assignment.features.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.R
import com.assignment.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
@AndroidEntryPoint
class NewsListFragment : Fragment(R.layout.fragment_news_list) {

    private val viewModel: NewsListViewModel by viewModels()
    lateinit var binding: FragmentNewsListBinding

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

        binding.apply {
            newsRcv.apply {
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {

                true
            }
            R.id.filter_day->{

                true
            }
            R.id.filter_month->{

                true
            }
            R.id.filter_week->{

                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}