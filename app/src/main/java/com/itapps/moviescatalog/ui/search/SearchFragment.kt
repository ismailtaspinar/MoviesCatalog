package com.itapps.moviescatalog.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.itapps.moviescatalog.adapter.MovieAdapter
import com.itapps.moviescatalog.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel : SearchViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        movieAdapter = MovieAdapter()

        binding.movieRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            )
            adapter = movieAdapter
        }
        binding.loadingPanel.visibility = View.GONE

        searchViewModel.movies.observe(viewLifecycleOwner){ movies ->
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, movies)
            movieAdapter.notifyDataSetChanged()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.input.apply {
            setOnLeftDrawableClickListener {
                observeMovies()
            }
            setOnRightDrawableClickListener {
                setText("")
            }
            setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
                ) {
                    handleEnterKeyPressed()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }

    }


    private fun observeMovies() {
        searchViewModel.getMovies(binding.input.text.toString())
    }

    private fun handleEnterKeyPressed() {
        observeMovies()
        binding.input.isCursorVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}