package com.musya.moviez.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "episodeEntity")
@Parcelize
data class EpisodeEntity (
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "episodeId")
        var episodeId: Int,

        @ColumnInfo(name = "fieldTvShowId")
        var fieldTvShowId: String,

        @ColumnInfo(name = "episodeNumber")
        var episodeNumber: Int,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "synopsis")
        var synopsis: String
        ):Parcelable