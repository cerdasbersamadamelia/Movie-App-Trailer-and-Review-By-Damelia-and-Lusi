package com.lusi.movieapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lusi.movieapp.R
import com.lusi.movieapp.model.Constant
import com.lusi.movieapp.model.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_main.view.*

class MainAdapter (var movies: ArrayList<MovieModel>, var listener: OnAdapterListener): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val TAG: String = "MainAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
    )

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        val posterPath = Constant.POSTER_PATH + movie.poster_path

        Picasso.get()
            .load(posterPath)
            .into( holder.view.image_poster );

        holder.view.image_poster.setOnClickListener{
            listener.onClick(movie)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bind(movies: MovieModel) {
            view.text_title.text = movies.title

        }
    }

    public fun setData(newMovies: List<MovieModel>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    public fun setDataNextPage(newMovies: List<MovieModel>) {
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(movie: MovieModel)
    }

}