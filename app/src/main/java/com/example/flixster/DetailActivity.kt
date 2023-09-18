package com.example.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView


private const val TAG = "DetailActivity"
class DetailActivity : AppCompatActivity(){

    private lateinit var tvTitle: TextView
    private lateinit var tvOverview: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var tvVoteAverage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        tvTitle = findViewById(R.id.title)
        tvOverview = findViewById(R.id.overview)
        tvReleaseDate = findViewById(R.id.releaseDate)
        tvVoteAverage = findViewById(R.id.voteAverage)

        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie
        Log.i(TAG, "Movie is $movie")

        tvTitle.text = movie.title
        tvOverview.text = movie.overview
        tvReleaseDate.text = "Release date: ${movie.releaseDate}"
        tvVoteAverage.text = "Vote average: ${movie.voteAverage.toString()}"
    }
}
