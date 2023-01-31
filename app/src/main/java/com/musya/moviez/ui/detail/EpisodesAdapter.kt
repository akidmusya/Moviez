package com.musya.moviez.ui.detail

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musya.moviez.data.source.local.entity.EpisodeEntity
import com.musya.moviez.databinding.ItemsEpisodesBinding

class EpisodesAdapter: RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {
    private var listEpisodes = ArrayList<EpisodeEntity>()

    fun setEpisodes(episodes: List<EpisodeEntity>?){
        if (episodes == null) return
        this.listEpisodes.clear()
        this.listEpisodes.addAll(episodes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemEpisodeBinding = ItemsEpisodesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemEpisodeBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listEpisodes[position])
    }

    override fun getItemCount(): Int = listEpisodes.size

    class ViewHolder(private val binding: ItemsEpisodesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(episode: EpisodeEntity) {
            val epNumber = episode.episodeNumber
            with(binding){
                tvEpisodeNumber.text = if(epNumber < 10) "0$epNumber" else epNumber.toString()
                tvEpisodeTitle.text = episode.title
                tvEpisodeOverview.text = episode.synopsis

                itemView.setOnClickListener {
                    toggleEpisode(tvEpisodeOverview.visibility != View.VISIBLE)
                }
            }
        }

        private fun toggleEpisode(show: Boolean){
            if(show){
                with(binding){
                    tvEpisodeOverview.visibility = View.VISIBLE
                    tvEpisodeNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
                    arrowDown.scaleY = -1f
                }
            } else {
                with(binding){
                    tvEpisodeOverview.visibility = View.GONE
                    tvEpisodeNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
                    arrowDown.scaleY = 1f
                }
            }
        }
    }

}