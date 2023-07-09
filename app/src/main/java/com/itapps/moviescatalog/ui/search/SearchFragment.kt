package com.itapps.moviescatalog.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.itapps.moviescatalog.R
import com.itapps.moviescatalog.adapter.MovieAdapter
import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import com.itapps.moviescatalog.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel : SearchViewModel by viewModels()
    private val movieList = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.movieRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            )
            adapter = MovieAdapter(movieList)
        }
        binding.loadingPanel.visibility = View.GONE

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.input.apply {
            setOnLeftDrawableClickListener {
                if(movieList.isNotEmpty()){
                    movieList.clear()
                }
                searchMovie()
            }
            setOnRightDrawableClickListener {
                setText("")
            }
            setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
                ) {
                    // Perform the desired action when the Enter key is pressed
                    handleEnterKeyPressed()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }

    }

    private fun searchMovie(){
        searchViewModel.fetchMovies(binding.input.text.toString()){
            when(it){
                is Resource.Loading -> {
                    binding.loadingPanel.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    Toast.makeText(context,resources.getString(R.string.error_text),Toast.LENGTH_LONG).show()
                    binding.loadingPanel.visibility = View.GONE
                    println(it.exception)
                }
                is Resource.Success -> {
                    movieList.addAll((it.response as MovieResponse).data)
                    binding.movieRecycler.adapter = MovieAdapter(movieList)
                    binding.loadingPanel.visibility = View.GONE
                    println(movieList.size)

                }
            }
        }
    }

    private fun handleEnterKeyPressed() {
        searchMovie()
        binding.input.isCursorVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}