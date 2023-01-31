package com.musya.moviez.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.musya.moviez.R
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.databinding.ItemMoviesBinding
import com.musya.moviez.ui.detail.DetailActivity

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.ListViewHolder>(){

    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?){
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemMovieBinding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemMovieBinding)
    }


    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(listMovies[position])
    }

    class ListViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageBaseUrl = "https://image.tmdb.org/t/p/w200"
        fun bindView(movie: MovieEntity) {
            with(binding){
                tvJudul.text = movie.title
                tvRating.text = movie.rate.toString()
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    with(intent){
                        putExtra(DetailActivity.EXTRA_SERIES, false)
                        putExtra(DetailActivity.EXTRA_MOVIE, movie.movieId)
                        putExtra(DetailActivity.EXTRA_FIELD_ID, movie.id)
                    }
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(imageBaseUrl+movie.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_broken_image)
                        .into(imageThumbnail)
            }
        }

    }

}