package com.musya.moviez.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.musya.moviez.ui.movies.MoviesFragment
import com.musya.moviez.ui.favorite.FavoriteFragment
import com.musya.moviez.ui.tvshow.TvShowFragment

class SectionPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment  =
            when (position){
                0 -> MoviesFragment()
                1 -> TvShowFragment()
                2 -> FavoriteFragment()
                else -> Fragment()
            }

}
