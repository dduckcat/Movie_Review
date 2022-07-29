package com.example.moviereview.presentation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviereview.R
import com.example.moviereview.databinding.ItemMovieBinding
import com.example.moviereview.remotedatasource.data.Result

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var mListMovie: MutableList<Result> = mutableListOf()
    private var listMovie: List<Result> = mListMovie

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {



        val elem = listMovie[position]
        holder.bind(elem)
        val image = holder.iconMovie

        Glide.with(image)
            .load(elem.multimedia.src)
            .centerCrop()
            .placeholder(R.drawable.ic_load)
            .error(R.drawable.ic_error)
            .into(image)
    }

    override fun getItemCount(): Int = listMovie.size


    fun submitList(movies: List<Result>) {
        listMovie = movies
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)
        var iconMovie = binding.imageView
        fun bind(movie: Result) {
            binding.textTitle.text = movie.display_title
            binding.textDescription.text = movie.summary_short
        }
    }

    abstract class PaginationScrollListener(var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

        abstract fun isLastPage(): Boolean

        abstract fun isLoading(): Boolean

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (!isLoading() && !isLastPage()) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    loadMoreItems()
                }
            }
        }
        abstract fun loadMoreItems()
    }

}