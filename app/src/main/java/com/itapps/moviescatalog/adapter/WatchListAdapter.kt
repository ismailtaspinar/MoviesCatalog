package com.itapps.moviescatalog.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.itapps.moviescatalog.R
import com.itapps.moviescatalog.common.Constants.BASE_POSTER
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.databinding.WatchlistItemBinding
import com.squareup.picasso.Picasso

class WatchListAdapter(
    private val movieList : List<Movie>,
    val picasso: Picasso
    ) : RecyclerView.Adapter<WatchListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val watchlistItemBinding = WatchlistItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(watchlistItemBinding,parent.findNavController())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position],picasso)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(
        private val watchlistItemBinding: WatchlistItemBinding,
        val navController: NavController
    ) : RecyclerView.ViewHolder(watchlistItemBinding.root) {

        fun bind(movie: Movie,picasso: Picasso){
            watchlistItemBinding.apply {
                picasso.load(BASE_POSTER+movie.poster_path).into(image)
                title.text = movie.title
                root.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("id",movie.id)
                    bundle.putBoolean("isSaved",true)
                    navController.navigate(R.id.action_navigation_watchlist_to_detailsFragment,bundle)
                }
            }
        }
    }
}