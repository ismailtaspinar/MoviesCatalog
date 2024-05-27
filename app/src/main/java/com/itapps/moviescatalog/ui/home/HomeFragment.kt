package com.itapps.moviescatalog.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.itapps.moviescatalog.R
import com.itapps.moviescatalog.adapter.SliderAdapter
import com.itapps.moviescatalog.common.Constants.UPCOMING_POSTER
import com.itapps.moviescatalog.common.Resource
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.data.model.MovieResponse
import com.itapps.moviescatalog.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private  val viewModel: HomeViewModel by activityViewModels()
    @Inject
    lateinit var picasso: Picasso
    private  var movieSliderList = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.loadingPanel.visibility = View.GONE

        viewModel.apply {
            if(!playingMovies.isInitialized) getPlayingMovies()
            if(!upcomingMovies.isInitialized)  getUpcomingMovies()
            if(!topMovies.isInitialized) getTopMovies()
        }
        observeUpcomingMovies()
        observeMovies()
        observeTopRatedMovies()
        observeDiscoveredMovies()

        return binding.root
    }

    private fun observeDiscoveredMovies() {
        viewModel.discoveredMovies.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    binding.loadingPanel.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.loadingPanel.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.loadingPanel.visibility = View.GONE
                    val movies = (it.response as MovieResponse).data
                    for(i in movies){
                        val cardView = createCardView(
                            i,R.layout.upcoming_item,binding.containerLayout
                        )
                        binding.discoveredContainerLayout.addView(cardView)
                    }
                }
            }
        }
    }

    private fun observeUpcomingMovies(){
        viewModel.upcomingMovies.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    binding.loadingPanel.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.loadingPanel.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.loadingPanel.visibility = View.GONE
                   val movies = (it.response as MovieResponse).data
                    for(i in movies){
                        val cardView = createCardView(
                            i,R.layout.upcoming_item,binding.containerLayout
                        )
                        binding.containerLayout.addView(cardView)
                    }
                }
            }
        }
    }

    private fun observeTopRatedMovies(){
        viewModel.topMovies.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    binding.loadingPanel.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.loadingPanel.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.loadingPanel.visibility = View.GONE
                    val movies = (it.response as MovieResponse).data
                    for(i in movies){
                        val cardView = createCardView(
                            i,R.layout.top_rated_item,binding.containerLayoutTopRated
                        )
                        binding.containerLayoutTopRated.addView(cardView)
                    }
                }
            }
        }
    }

    private fun createCardView(item: Movie,resource : Int,view: ViewGroup): CardView {
        val cardView = LayoutInflater.from(context).inflate(
            resource,view, false
        ) as CardView
        val imageView = cardView.findViewById<ImageView>(R.id.imageView)
        val textView = cardView.findViewById<TextView>(R.id.textView)
        val rateText = cardView.findViewById<TextView>(R.id.rateText)

        picasso.load(UPCOMING_POSTER+item.poster_path).into(imageView)
        textView.text = item.title
        rateText.text = ((item.vote.toFloat() * 10).roundToInt() / 10.0).toString()




        cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id",item.id)
            bundle.putBoolean("isSaved",false)
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
        }

        return cardView
    }

    private fun observeMovies(){
        viewModel.playingMovies.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> {
                    binding.loadingPanel.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.loadingPanel.visibility = View.GONE
                    binding.viewpager.adapter = SliderAdapter(
                        (movieSliderList),picasso
                    )
                }
                is Resource.Success -> {
                    binding.viewpager.adapter = SliderAdapter(
                        (it.response as MovieResponse).data,picasso
                    )
                    binding.loadingPanel.visibility = View.GONE
                    val indicator = binding.indicator
                    indicator.setViewPager(binding.viewpager)
                    //binding.viewpager.adapter?.notifyDataSetChanged()
                }
            }
        }
    }



}