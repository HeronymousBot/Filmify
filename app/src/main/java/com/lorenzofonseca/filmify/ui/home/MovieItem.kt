package com.lorenzofonseca.filmify.ui.home

import android.view.View
import android.widget.ImageView
import com.lorenzofonseca.data.util.Constants
import com.lorenzofonseca.domain.entities.MovieResultModel
import com.lorenzofonseca.filmify.R
import com.lorenzofonseca.filmify.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem

class MovieItem(private val movie: MovieResultModel, val onSelectedItem: (id: Int) -> Unit) :
    BindableItem<ItemMovieBinding>() {

    override fun bind(viewBinding: ItemMovieBinding, position: Int) {
        loadImage(viewBinding.ivMoviePoster, Constants.HOST_POSTER_URL + movie.posterPath)

        viewBinding.ivMoviePoster.setOnClickListener {
            onSelectedItem(movie.id)
        }
    }

    override fun getLayout(): Int = R.layout.item_movie

    override fun initializeViewBinding(view: View): ItemMovieBinding {
        return ItemMovieBinding.bind(view)
    }

    companion object {
        fun loadImage(view: ImageView, url: String) {
            Picasso.get().load(url).into(view)
        }
    }
}