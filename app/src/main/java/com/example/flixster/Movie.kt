package com.example.flixster

import com.google.gson.annotations.SerializedName

class Movie {
    @SerializedName("id")
    var id: Int? = null

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("poster_path")
    var posterPath: String? = null

    @JvmField
    @SerializedName("overview")
    var overview: String? = null
}