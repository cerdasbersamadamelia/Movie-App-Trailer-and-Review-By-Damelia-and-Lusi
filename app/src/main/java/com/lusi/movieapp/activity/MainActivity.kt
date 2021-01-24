package com.lusi.movieapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.lusi.movieapp.R
import com.lusi.movieapp.adapter.MainAdapter
import com.lusi.movieapp.model.Constant
import com.lusi.movieapp.model.MovieModel
import com.lusi.movieapp.model.MovieResponse
import com.lusi.movieapp.retrofit.ApiService
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val moviePopular = 0
const val movieNowPlaying = 1
const val movieTopRated = 2
const val movieUpcoming = 3

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    lateinit var mainAdapter: MainAdapter
    private var movieCategory = 0
    private val api = ApiService().endpoint
    private var isScrolling = false
    private var currentPage = 1
    private var totalPages = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupRecyclerView()
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        getMovie()
        showLoadingNextPage(false)
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter(arrayListOf(), object : MainAdapter.OnAdapterListener {
            override fun onClick(movie: MovieModel) {
                Constant.MOVIE_ID = movie.id!!
                Constant.MOVIE_TITLE = movie.title!!
                startActivity(Intent(applicationContext, DetailActivity::class.java))
            }

        })
        list_movie.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = mainAdapter
        }
    }

    private fun setupListener() {
        scrollView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener{
            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight) {
                    if (!isScrolling) {
                        if ( currentPage <= totalPages) {
                            getMovieNextPage()
                        }
                    }
                }
            }

        })
    }

    private fun getMovie() {

        scrollView.scrollTo(0,0)
        currentPage = 1
        showLoading(true)

        var apiCall: Call<MovieResponse>? = null
        when (movieCategory) {
            moviePopular -> {
                apiCall = api.getMoviePopular(Constant.API_KEY, 1)
            }
            movieNowPlaying -> {
                apiCall = api.getMovieNowPlaying(Constant.API_KEY, 1)
            }
            movieTopRated -> {
                apiCall = api.getMovieTopRated(Constant.API_KEY, 1)
            }
            movieUpcoming -> {
                apiCall = api.getMovieUpcoming(Constant.API_KEY, 1)
            }
        }

        apiCall!!
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d(TAG, "errorResponse: $t")
                    showLoading(false)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        showMovie(response.body()!! )
                    }
                }

            })
    }

    private fun getMovieNextPage() {

        currentPage += 1
        showLoadingNextPage(true)

        var apiCall: Call<MovieResponse>? = null
        when (movieCategory) {
            moviePopular -> {
                apiCall = api.getMoviePopular(Constant.API_KEY, currentPage)
            }
            movieNowPlaying -> {
                apiCall = api.getMovieNowPlaying(Constant.API_KEY, currentPage)
            }
            movieTopRated -> {
                apiCall = api.getMovieTopRated(Constant.API_KEY, currentPage)
            }
            movieUpcoming -> {
                apiCall = api.getMovieUpcoming(Constant.API_KEY, currentPage)
            }
        }

        apiCall!!
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d(TAG, "errorResponse: $t")
                    showLoadingNextPage(false)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    showLoadingNextPage(false)
                    if (response.isSuccessful) {
                        showMovieNextPage(response.body()!! )
                    }
                }

            })
    }

    fun showLoading(loading: Boolean) {
        when(loading) {
            true -> progress_movie.visibility = View.VISIBLE
            false -> progress_movie.visibility = View.GONE
        }
    }

    fun showLoadingNextPage(loading: Boolean) {
        when(loading) {
            true -> {
                isScrolling = true
                progress_movie_next_page.visibility = View.VISIBLE
            }
            false -> {
                isScrolling = false
                progress_movie_next_page.visibility = View.GONE
            }
        }
    }

    fun showMovie(response: MovieResponse) {
        totalPages = response.total_pages!!.toInt()
        mainAdapter.setData(response.results)
    }

    fun showMovieNextPage(response: MovieResponse) {
        totalPages = response.total_pages!!.toInt()
        mainAdapter.setDataNextPage(response.results)
        showMessage("Page $currentPage" )
    }

    fun showMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_popular -> {
                showMessage("movie popular selected")
                movieCategory = moviePopular
                getMovie()
                true
            }
            R.id.action_now_playing -> {
                showMessage("movie now playing selected")
                movieCategory = movieNowPlaying
                getMovie()
                true
            }
            R.id.action_top_rated -> {
                showMessage("movie top rated selected")
                movieCategory = movieTopRated
                getMovie()
                true
            }
            R.id.action_upcoming -> {
                showMessage("movie upcoming selected")
                movieCategory = movieUpcoming
                getMovie()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}