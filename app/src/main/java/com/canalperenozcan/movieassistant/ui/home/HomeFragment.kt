package com.canalperenozcan.movieassistant.ui.home

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
import com.canalperenozcan.movieassistant.R
import com.canalperenozcan.movieassistant.adapter.SliderAdapter
import com.canalperenozcan.movieassistant.common.Constants.UPCOMING_POSTER
import com.canalperenozcan.movieassistant.common.Resource
import com.canalperenozcan.movieassistant.data.model.Movie
import com.canalperenozcan.movieassistant.data.model.MovieResponse
import com.canalperenozcan.movieassistant.databinding.FragmentHomeBinding
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

        return binding.root
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