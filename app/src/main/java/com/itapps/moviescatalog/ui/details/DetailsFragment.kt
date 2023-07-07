package com.itapps.moviescatalog.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.itapps.moviescatalog.R
import com.itapps.moviescatalog.common.Constants.BASE_DETAILS
import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.databinding.FragmentDetailsBinding
import com.itapps.moviescatalog.databinding.FragmentSearchBinding
import com.itapps.moviescatalog.ui.search.SearchViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel : SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        getDetails()


        return binding.root
    }

    private fun getDetails(){
        val id = arguments?.getString("id")

        searchViewModel.fetchDetails(id!!){
            when(it){
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    Toast.makeText(context,resources.getString(R.string.error_text),Toast.LENGTH_LONG).show()
                    binding.progress.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    loadViews(it.response as Movie)
                }
            }
        }
    }

    private fun loadViews(movie : Movie) {
        Picasso
            .get()
            .load(BASE_DETAILS+movie.backdrop_path)
            .fit()
            .into(binding.image)
        binding.date.text = movie.release_date
        binding.title.text = movie.title
        binding.nominator.text = ((movie.vote.toFloat() * 10).roundToInt() / 10.0).toString()
        binding.description.text = movie.overview
        println(BASE_DETAILS+movie.backdrop_path)
    }

}