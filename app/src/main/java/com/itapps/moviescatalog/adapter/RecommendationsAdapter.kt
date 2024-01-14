package com.itapps.moviescatalog.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.itapps.moviescatalog.R
import com.itapps.moviescatalog.common.Constants
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.databinding.UpcomingItemBinding
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class RecommendationsAdapter(
    private val movieList : List<Movie>
) : RecyclerView.Adapter<RecommendationsAdapter.MovieHolder>() {

    inner class MovieHolder(
        private val itemBinding: UpcomingItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie : Movie) {
            itemBinding.apply {
                Picasso.get().load(Constants.UPCOMING_POSTER +movie.poster_path).into(imageView)
                textView.text = movie.title
                rateText.text = ((movie.vote.toFloat() * 10).roundToInt() / 10.0).toString()

                root.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("id",movie.id)
                    bundle.putBoolean("isSaved",false)
                    findNavController(itemBinding.root)
                        .navigate(R.id.action_detailsFragment_self,bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = UpcomingItemBinding.inflate(LayoutInflater.from(parent.context))
        return MovieHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList.get(position))
    }
}