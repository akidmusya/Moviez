package com.musya.moviez.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.musya.moviez.databinding.FragmentMoviesBinding
import com.musya.moviez.viewmodel.ViewModelFactory
import com.musya.moviez.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MoviesFragment : DaggerFragment() {
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){

            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            val movieAdapter = MoviesAdapter()

            viewModel.getMovies().observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> fragmentMoviesBinding.progressBar.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentMoviesBinding.progressBar.visibility = View.GONE
                            movieAdapter.setMovies(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentMoviesBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


            with(fragmentMoviesBinding.rvMovies){
                layoutManager = GridLayoutManager(context, 2)
                adapter = movieAdapter
            }
        }
    }
}