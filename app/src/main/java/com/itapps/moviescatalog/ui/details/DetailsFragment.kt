package com.itapps.moviescatalog.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.itapps.moviescatalog.R
import com.itapps.moviescatalog.common.Constants.BASE_DETAILS
import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel : DetailsViewModel by viewModels()
    private lateinit var movie: Movie
    private var menuProvider: MenuProvider? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater,container,false)

        val isSaved = arguments?.getBoolean("isSaved")
        val id = arguments?.getString("id")

        if(isSaved!!) detailsViewModel.getMovieById(id!!) else getDetails()

        detailsViewModel.movie.observe(viewLifecycleOwner){
            movie = it
            loadViews(movie)
        }

        return binding.root
    }

    private fun getDetails(){
        val id = arguments?.getString("id")

        detailsViewModel.fetchDetails(id!!){
            when(it){
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    Toast.makeText(context,resources.getString(R.string.error_text),Toast.LENGTH_LONG).show()
                    binding.progress.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    movie = it.response as Movie
                    loadViews(movie)
                }
            }
        }
    }

    private fun loadViews(movie : Movie) {

        binding.apply {
            Picasso
                .get()
                .load(BASE_DETAILS+movie.backdrop_path)
                .fit()
                .into(image)
            date.text = movie.release_date
            title.text = movie.title
            nominator.text = ((movie.vote.toFloat() * 10).roundToInt() / 10.0).toString()
            description.text = movie.overview
            setupMenu()
        }
    }

    private fun setupMenu() {
        menuProvider = object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fav_menu, menu)
                if(movie.isWatchList) {
                   menu.getItem(0).applyColor(R.color.red)
                }
                if(movie.isFavorite){
                   menu.getItem(1).applyColor(R.color.red)
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Validate and handle the selected menu item
                when(menuItem.itemId){
                    R.id.watchlist_button -> {
                            if(movie.isWatchList) {
                                movie.isWatchList=false
                                menuItem.applyColor(R.color.black)
                            }
                            else {
                                movie.isWatchList=true
                                menuItem.applyColor(R.color.red)
                            }
                            detailsViewModel.addMovie(movie)
                    }
                    R.id.fav_button -> {
                            if(movie.isFavorite){
                                movie.isFavorite=false
                                menuItem.applyColor(R.color.black)
                            }
                            else {
                                movie.isFavorite=true
                                menuItem.applyColor(R.color.red)
                            }
                        detailsViewModel.addMovie(movie)
                    }
                    else ->{
                        val navController = findNavController()
                        return menuItem.onNavDestinationSelected(navController)
                    }

                }
                return true
            }
        }
        (requireActivity() as MenuHost).addMenuProvider(menuProvider!!, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun MenuItem.applyColor(color: Int) {
        icon?.setTint(resources.getColor(color,requireContext().theme))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (menuProvider != null) {
            (requireActivity() as MenuHost).removeMenuProvider(menuProvider!!)
            menuProvider = null
        }
    }

}