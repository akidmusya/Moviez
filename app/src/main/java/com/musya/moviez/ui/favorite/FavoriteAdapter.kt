package com.musya.moviez.ui.favorite

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.musya.moviez.R
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.databinding.ItemFavoriteBinding
import com.musya.moviez.ui.detail.DetailActivity

@Suppress("DEPRECATION")
class FavoriteAdapter: PagedListAdapter<MovieEntity, FavoriteAdapter.ListViewHolder>(DIFF_CALLBACK) {
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null){
            holder.bind(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemFavoriteBinding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemFavoriteBinding)
    }

    class ListViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        private val imageBaseUrl = "https://image.tmdb.org/t/p/w92"
        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieEntity) {
            val isSeries = movie.episodesCount > 0

            with(binding){
                tvFavMovieTitle.text = movie.title
                tvFavMovieRate.text = movie.rate.toString()
                if (isSeries){
                    tvEpisodesOrTimes.text = movie.episodesCount.toString()+" Episodes"
                } else {
                    tvEpisodesOrTimes.text = movie.runtime.toString()+" Minutes"
                    tvEpisodesOrTimes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_access_time, 0,0,0)
                }
                Glide.with(itemView.context)
                        .load(imageBaseUrl+movie.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_broken_image)
                        .into(imageFavMovie)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                with(intent){
                    putExtra(DetailActivity.EXTRA_SERIES, movie.episodesCount > 0)
                    putExtra(DetailActivity.EXTRA_MOVIE, movie.movieId)
                    putExtra(DetailActivity.EXTRA_FIELD_ID, movie.id)
                }
                itemView.context.startActivity(intent)
            }
        }

    }
}