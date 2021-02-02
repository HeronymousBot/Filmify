package com.lorenzofonseca.filmify.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import com.lorenzofonseca.data.util.Constants
import com.lorenzofonseca.filmify.databinding.FragmentMovieDetailsBinding
import com.lorenzofonseca.filmify.utils.invisible
import com.lorenzofonseca.filmify.utils.subscribe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailsFragment : Fragment(), LifecycleOwner {
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailViewModel by viewModel {
        parametersOf(args.movieId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieDetails()
        subscribeEvents()
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMovieDetailsBinding.inflate(inflater)
        .apply {
            viewModel = this@MovieDetailsFragment.viewModel
            lifecycleOwner = this@MovieDetailsFragment
        }.root

    private fun subscribeEvents() {
        viewModel.onSuccessEvent.subscribe(this, {
            shMoviePlaceHolder.invisible()
            Picasso.get().load(Constants.HOST_POSTER_URL + it.posterPath).into(ivMoviePoster)
        })

    }

}