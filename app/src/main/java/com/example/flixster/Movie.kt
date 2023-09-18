package com.example.flixster

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Movies (
    @SerialName("results")
    val results: List<Movie>?
) : java.io.Serializable

@Keep
@Serializable
data class Movie (
    @SerialName("id")
    val id: Int?,

    @SerialName("title")
    var title: String?,

    @SerialName("poster_path")
    var posterPath: String?,

    @SerialName("overview")
    var overview: String?,

    @SerialName("release_date")
    val releaseDate: String?,

    @SerialName("vote_average")
    val voteAverage: String?
) : java.io.Serializable
