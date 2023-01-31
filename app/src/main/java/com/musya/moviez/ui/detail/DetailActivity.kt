package com.musya.moviez.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.musya.moviez.R
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.databinding.ActivityDetailBinding
import com.musya.moviez.databinding.ContentDetailMovieBinding
import com.musya.moviez.viewmodel.ViewModelFactory
import com.musya.moviez.vo.Status
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    companion object {
        const val EXTRA_FIELD_ID = "extra_field_id"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_SERIES = "extra_series"
        const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w780"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailBinding: ContentDetailMovieBinding
    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailBinding = activityDetailBinding.detailMovie

        setContentView(activityDetailBinding.root)

        activityDetailBinding.btnBack.setOnClickListener{
            onBackPressed()
        }

        val epAdapter = EpisodesAdapter()

        viewModel = ViewModelProvider(
                this,
                factory
        )[DetailViewModel::class.java]

        detailBinding.rvEpisodes.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = EpisodesAdapter()
        }

        activityDetailBinding.progressBar.visibility = View.VISIBLE
        activityDetailBinding.detailContent.visibility = View.INVISIBLE
        val extras = intent.extras
        if (extras != null){
            val id = extras.getString(EXTRA_MOVIE)
            val isSeries = extras.getBoolean(EXTRA_SERIES)
            val fieldId = extras.getInt(EXTRA_FIELD_ID)
            if (id != null){
                if (isSeries){
                    viewModel.getTvShow(fieldId, id.toInt()).observe(this) { tvShow ->
                        if (tvShow != null) {
                            when (tvShow.status) {
                                Status.LOADING -> activityDetailBinding.progressBar.visibility =
                                    View.VISIBLE
                                Status.SUCCESS -> {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    activityDetailBinding.detailContent.visibility = View.VISIBLE

                                    viewModel.getCasts(id.toInt(), isSeries)
                                        .observe(this) { movieWithCast ->
                                            if (movieWithCast != null) {
                                                when (movieWithCast.status) {
                                                    Status.SUCCESS -> populateMovie(
                                                        movieWithCast.data,
                                                        isSeries
                                                    )
                                                    Status.ERROR -> {
                                                        activityDetailBinding.progressBar.visibility =
                                                            View.GONE
                                                        Toast.makeText(
                                                            this,
                                                            "Terjadi Kesalahan",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                    Status.LOADING -> {}
                                                }
                                            }

                                        }

                                }
                                Status.ERROR -> {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    }

                    viewModel.getEpisodes(id.toInt()).observe(this) { episode ->
                        if (episode != null) {
                            when (episode.status) {
                                Status.LOADING -> activityDetailBinding.progressBar.visibility =
                                    View.VISIBLE
                                Status.SUCCESS -> {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    activityDetailBinding.detailContent.visibility = View.VISIBLE
                                    epAdapter.setEpisodes(episode.data)
                                    epAdapter.notifyDataSetChanged()
                                }
                                Status.ERROR -> {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }

                    }

                    with(detailBinding.rvEpisodes){
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = epAdapter
                    }
                } else {
                    viewModel.getMovie(fieldId, id.toInt()).observe(this) { movies ->
                        if (movies != null) {
                            when (movies.status) {
                                Status.LOADING -> activityDetailBinding.progressBar.visibility =
                                    View.VISIBLE
                                Status.SUCCESS -> {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    activityDetailBinding.detailContent.visibility = View.VISIBLE

                                    viewModel.getCasts(id.toInt(), false).observe(
                                        this
                                    ) { moviesWithCast ->
                                        if (moviesWithCast != null) {
                                            when (moviesWithCast.status) {
                                                Status.SUCCESS -> {
                                                    populateMovie(movies.data, isSeries)
                                                }
                                                Status.ERROR -> {}
                                                Status.LOADING -> {}
                                            }
                                        }
                                    }
                                }
                                Status.ERROR -> {
                                    activityDetailBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    }
                    detailBinding.tvEpisodes.visibility = View.GONE
                }
            }
        }
    }

    private fun populateMovie(movie: MovieEntity?, isSeries: Boolean) {
        detailBinding.tvMovieTitle.text = movie?.title
        detailBinding.tvRating.text = movie?.rate.toString()
        detailBinding.tvReleaseDate.text = movie?.releaseDate
        detailBinding.tvSynopsis.text = movie?.synopsis
        detailBinding.tvGenres.text = movie?.genres
        detailBinding.tvActors.text = movie?.actors
        Glide.with(this)
                .load(BASE_POSTER_URL +movie?.posterPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_broken_image)
                .into(activityDetailBinding.posterMovie)

        val statusFavorite = movie?.isFavorite ?: false
        setFavoriteState(statusFavorite)

        if (movie != null){
            activityDetailBinding.btnFavorite.setOnClickListener{
                setFavorite(movie, isSeries)
            }
        }
    }

    private fun setFavoriteState(status: Boolean){
        if (status){
            activityDetailBinding.btnFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            activityDetailBinding.btnFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun showSnackBar(msg: String){
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun setFavorite(movie: MovieEntity, isSeries: Boolean){
        if (movie.isFavorite){
            showSnackBar("${movie.title} removed from favorite")
        } else {
            showSnackBar("${movie.title} added to favorite")
        }

        if (isSeries){
            viewModel.setFavoriteTvShow(movie)
        } else {
            viewModel.setFavoriteMovie(movie)
        }
    }
}