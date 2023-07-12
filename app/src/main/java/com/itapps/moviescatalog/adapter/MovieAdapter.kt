package com.itapps.moviescatalog.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itapps.moviescatalog.R
import com.itapps.moviescatalog.common.Constants.BASE_POSTER
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.databinding.MovieItemBinding
import com.squareup.picasso.Picasso




class MovieAdapter(val picasso: Picasso) :
    PagingDataAdapter<Movie, MovieAdapter.ViewHolder>(MovieDiffCallback()) {
    private lateinit var movieItemBinding: MovieItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        movieItemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(movieItemBinding,parent.findNavController())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,picasso)
    }



    class ViewHolder(val movieItemBinding: MovieItemBinding,val navController: NavController) : RecyclerView.ViewHolder(movieItemBinding.root){


        fun bind(item : Movie,picasso: Picasso){
            movieItemBinding.text.text = item.title
            picasso
                .load(BASE_POSTER+item.poster_path)
                .placeholder(R.drawable.image_file)
                .into(movieItemBinding.image)
            setListeners(item)
        }
        private fun setListeners(item : Movie){
             movieItemBinding.root.setOnClickListener {
                 val bundle = Bundle()
                 bundle.putString("id",item.id)
                 bundle.putBoolean("isSaved",false)
                 navController.navigate(R.id.action_navigation_search_to_detailsFragment,bundle)
             }
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
