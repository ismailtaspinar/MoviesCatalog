package com.itapps.moviescatalog.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.itapps.moviescatalog.R
import com.itapps.moviescatalog.data.model.Movie
import com.itapps.moviescatalog.databinding.MovieItemBinding

class MovieAdapter(private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var movieItemBinding: MovieItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        movieItemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(movieItemBinding,parent.findNavController())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    class ViewHolder(val movieItemBinding: MovieItemBinding,val navController: NavController) : RecyclerView.ViewHolder(movieItemBinding.root){


        fun bind(item : Movie){
            movieItemBinding.text.text = item.title
            setListeners(item)
        }
        private fun setListeners(item : Movie){
             movieItemBinding.root.setOnClickListener {
                 val bundle = Bundle()
                 bundle.putString("id",item.id)
                 navController.navigate(R.id.action_navigation_search_to_detailsFragment,bundle)
             }
        }
    }
}
