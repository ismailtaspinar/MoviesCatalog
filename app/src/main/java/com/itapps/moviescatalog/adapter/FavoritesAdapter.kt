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
import com.itapps.moviescatalog.databinding.FavoriteItemBinding
import com.squareup.picasso.Picasso

class FavoritesAdapter(private val movieList: List<Movie>) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val favoriteItemBinding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(favoriteItemBinding,parent.findNavController())
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    class ViewHolder(
        private val favoriteItemBinding: FavoriteItemBinding,
        val navController: NavController
        ) : RecyclerView.ViewHolder(favoriteItemBinding.root) {

            fun bind(movie: Movie){
                favoriteItemBinding.apply {
                    Picasso.get().load(BASE_POSTER+movie.poster_path).into(image)
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