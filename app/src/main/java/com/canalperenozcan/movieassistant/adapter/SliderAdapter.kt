package com.canalperenozcan.movieassistant.adapter

import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.canalperenozcan.movieassistant.R
import com.canalperenozcan.movieassistant.common.Constants.BASE_DETAILS
import com.canalperenozcan.movieassistant.data.model.Movie
import com.canalperenozcan.movieassistant.databinding.SliderItemBinding
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class SliderAdapter(
    private var movieList: List<Movie>,
    private val picasso: Picasso
    ) : PagerAdapter() {
    override fun getCount(): Int {
        return movieList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = SliderItemBinding.inflate(LayoutInflater.from(container.context))
        val movie = movieList[position]
        binding.data = movie
        val size = Point()
        val width = (size.x) / 2
        val height = ((size.y) / 2.2).roundToInt()
        picasso
            .load(BASE_DETAILS+movieList[position].backdrop_path)
            .into(binding.image)

        binding.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id",movie.id)
            bundle.putBoolean("isSaved",false)
            container.findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
        }

        val vp = container as ViewPager
        vp.addView(binding.root, 0)

        return binding.root
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }


}