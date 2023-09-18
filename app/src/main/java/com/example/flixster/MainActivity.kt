package com.example.flixster

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONException
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable


private const val TAG = "MainActivity"
private const val NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/popular"

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<Movie>()
    private lateinit var rvMovies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvMovies = findViewById(R.id.rvMovies)

        val movieAdapter = MovieAdapter(this, movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
        client.get(NOW_PLAYING_URL, params, object: JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?)
            {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON)
            {
                Log.i(TAG, "onSuccess: JSON data $json")
                try {
                    val movieJsonArray = json.jsonObject.toString()
                    Log.i(TAG, "movieJsonArray: $movieJsonArray")

//                    val parsedJson = createJson().decodeFromString<Movies>(
//                        movieJsonArray
//                    )
//                    Log.i(TAG, paredJson)
                    val parsedJson = createJson().decodeFromString(Movies.serializer(), movieJsonArray)

                    // Save the movies
                    parsedJson.results?.let { list ->
                        for (model in list) {
                            model.posterPath = "https://image.tmdb.org/t/p/w500/${model.posterPath}"
                        }
                        movies.addAll(list)

                        // Reload the screen
                        movieAdapter.notifyDataSetChanged()
                    }
//
//                    val gson = Gson()
//                    val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
//                    val models = gson.fromJson<List<Movie>>(movieJsonArray, arrayMovieType)
//                    Log.i(TAG, "models: ${models}")
//
//                    for (model in models) {
//                        model.posterPath = "https://image.tmdb.org/t/p/w500/${model.posterPath}"
//                    }
//                    movies.addAll(models)

//                    movieAdapter.notifyDataSetChanged()
                    Log.i(TAG, "Movie List $movies")
                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered exception $e")
                }
            }

        })
    }
}
