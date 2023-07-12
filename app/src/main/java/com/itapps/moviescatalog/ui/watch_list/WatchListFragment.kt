package com.itapps.moviescatalog.ui.watch_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.itapps.moviescatalog.adapter.WatchListAdapter
import com.itapps.moviescatalog.databinding.FragmentWatchlistBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WatchListFragment : Fragment() {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!
    private val watchListViewModel : WatchListViewModel by viewModels()
    @Inject lateinit var picasso: Picasso

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        watchListViewModel.apply {
            getWatchListMovies()
            movies.observe(viewLifecycleOwner){movies ->
                binding.movieRecycler.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    addItemDecoration(
                        DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                    )
                    adapter = WatchListAdapter(movies,picasso)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}