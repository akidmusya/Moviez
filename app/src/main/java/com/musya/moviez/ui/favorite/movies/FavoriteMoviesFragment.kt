package com.musya.moviez.ui.favorite.movies

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
import com.musya.moviez.databinding.FragmentFavoriteMoviesBinding
import com.musya.moviez.ui.favorite.FavoriteAdapter
import com.musya.moviez.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteMoviesFragment : DaggerFragment() {
    private lateinit var favoriteMoviesBinding: FragmentFavoriteMoviesBinding

    @Inject
    lateinit var factory : ViewModelFactory

    private val adapter : FavoriteAdapter by lazy {
        FavoriteAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteMoviesBinding = FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return favoriteMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null){
            val viewModel = ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]

            favoriteMoviesBinding.rvFavMovies.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(context)
            }

            viewModel.getFavMovies().observe(viewLifecycleOwner, { movies ->
                if(movies != null){
                    adapter.submitList(movies)
                    adapter.notifyDataSetChanged()
                }
            })

            val searchIcon = favoriteMoviesBinding.searchMovie.findViewById<ImageView>(R.id.search_mag_icon)
            searchIcon.setColorFilter(Color.RED)

            val cancelIcon = favoriteMoviesBinding.searchMovie.findViewById<ImageView>(R.id.search_close_btn)
            cancelIcon.setColorFilter(Color.RED)

            val tvSearch = favoriteMoviesBinding.searchMovie.findViewById<TextView>(R.id.search_src_text)
            tvSearch.setTextColor(Color.WHITE)

            favoriteMoviesBinding.searchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchMovie(newText ?: "").observe(viewLifecycleOwner, { movie ->
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