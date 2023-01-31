package com.musya.moviez.ui.tvshow

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

class TvAdapter: RecyclerView.Adapter<TvAdapter.ViewHolder>() {

    private var listTvShows = ArrayList<MovieEntity>()

    fun setTvShow(tvShow: List<MovieEntity>?){
        if (tvShow == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShow)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMovieBinding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listTvShows[position])
    }

    override fun getItemCount(): Int = listTvShows.size

    class ViewHolder(private val binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        private val imageBaseUrl = "https://image.tmdb.org/t/p/w200"
        fun bindView(movie: MovieEntity) {
            with(binding){
                tvJudul.text = movie.title
                tvRating.text = movie.rate.toString()
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    with(intent){
                        putExtra(DetailActivity.EXTRA_SERIES, true)
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