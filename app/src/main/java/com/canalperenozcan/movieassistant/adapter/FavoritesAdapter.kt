package com.canalperenozcan.movieassistant.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.canalperenozcan.movieassistant.R
import com.canalperenozcan.movieassistant.common.Constants.BASE_POSTER
import com.canalperenozcan.movieassistant.data.model.Movie
import com.canalperenozcan.movieassistant.databinding.FavoriteItemBinding
import com.squareup.picasso.Picasso

class FavoritesAdapter(private val movieList: List<Movie>,val picasso: Picasso) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val favoriteItemBinding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(favoriteItemBinding,parent.findNavController())
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position],picasso)
    }

    class ViewHolder(
        private val favoriteItemBinding: FavoriteItemBinding,
        val navController: NavController
        ) : RecyclerView.ViewHolder(favoriteItemBinding.root) {

            fun bind(movie: Movie,picasso: Picasso){
                favoriteItemBinding.apply {
                    picasso.load(BASE_POSTER+movie.poster_path).into(image)
                    title.text = movie.title
                    root.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putString("id",movie.id)
                        bundle.putBoolean("isSaved",true)
                        navController.navigate(R.id.action_navigation_favorites_to_detailsFragment,bundle)
                    }
                }
            }
    }
}