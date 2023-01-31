package com.musya.moviez.ui.favorite.tvshow

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.musya.moviez.R
import com.musya.moviez.databinding.FragmentFavoriteTvShowBinding
import com.musya.moviez.ui.favorite.FavoriteAdapter
import com.musya.moviez.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteTvShowFragment : DaggerFragment() {
    private lateinit var favoriteTvShowBinding: FragmentFavoriteTvShowBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val adapter: FavoriteAdapter by lazy {
        FavoriteAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        favoriteTvShowBinding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return favoriteTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            favoriteTvShowBinding.rvFavTvShow.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
            }

            viewModel.getFavTvShow().observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null){
                    adapter.submitList(tvShow)
                    adapter.notifyDataSetChanged()
                }
            })

            val searchIcon = favoriteTvShowBinding.searchTvShow.findViewById<ImageView>(R.id.search_mag_icon)
            searchIcon.setColorFilter(Color.RED)

            val cancelIcon = favoriteTvShowBinding.searchTvShow.findViewById<ImageView>(R.id.search_close_btn)
            cancelIcon.setColorFilter(Color.RED)

            val tvSearch = favoriteTvShowBinding.searchTvShow.findViewById<TextView>(R.id.search_src_text)
            tvSearch.setTextColor(Color.WHITE)

            favoriteTvShowBinding.searchTvShow.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchTvShow(newText ?: "").observe(viewLifecycleOwner, { movie ->
                        if (movie!= null){
                            adapter.submitList(movie)
                            adapter.notifyDataSetChanged()
                        }
                    })
                    return false
                }
            })
        }
    }
}