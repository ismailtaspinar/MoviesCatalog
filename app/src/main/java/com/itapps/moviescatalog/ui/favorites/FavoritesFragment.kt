package com.itapps.moviescatalog.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.itapps.moviescatalog.adapter.FavoritesAdapter
import com.itapps.moviescatalog.databinding.FragmentFavoritesBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val favoritesViewModel : FavoritesViewModel by viewModels()

    @Inject
    lateinit var picasso: Picasso

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favoritesViewModel.getFavoriteMovies()

        favoritesViewModel.movies.observe(viewLifecycleOwner) { movies ->
            binding.movieRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                addItemDecoration(
                    DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                )
                adapter = FavoritesAdapter(movies,picasso)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}