package com.musya.moviez.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movieEntities")
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "movieId")
    var movieId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "rate")
    var rate: Double,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "synopsis")
    var synopsis: String,

    @ColumnInfo(name = "actors")
    var actors: String,

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "runtime")
    var runtime: Int,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "episodeCount")
    var episodesCount: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
):Parcelable
