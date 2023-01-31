package com.musya.moviez.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.musya.moviez.databinding.FragmentFavoriteBinding
import dagger.android.support.DaggerFragment

class FavoriteFragment : DaggerFragment() {

    companion object{
        private val TAB_TITTLES = arrayOf("Movies", "Tv Shows")
    }

    private lateinit var binding: FragmentFavoriteBinding

    private val adapter: SectionPagerAdapter by lazy {
        SectionPagerAdapter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoriteViewPager.also {
            it.adapter = adapter
            it.isUserInputEnabled = false
            it.offscreenPageLimit = 2
        }

        TabLayoutMediator(binding.favoriteTabLayout, binding.favoriteViewPager) { tab, position ->
            tab.text = TAB_TITTLES[position]
        }.attach()

    }

}