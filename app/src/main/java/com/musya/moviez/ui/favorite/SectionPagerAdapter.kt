package com.musya.moviez.ui.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.musya.moviez.ui.favorite.movies.FavoriteMoviesFragment
import com.musya.moviez.ui.favorite.tvshow.FavoriteTvShowFragment

class SectionPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
            when(position){
                0 -> FavoriteMoviesFragment()
                1 -> FavoriteTvShowFragment()
                else -> Fragment()
            }

}