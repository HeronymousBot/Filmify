package com.lorenzofonseca.filmify.ui.home

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lorenzofonseca.domain.entities.GalleryModel
import com.lorenzofonseca.domain.entities.MovieResultModel
import com.lorenzofonseca.filmify.R
import com.lorenzofonseca.filmify.databinding.ItemGalleryBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class GalleryItem(
    private val gallery: GalleryModel,
    private val onSelectItem: (movieId: Int) -> Unit
) : BindableItem<ItemGalleryBinding>() {
    override fun bind(viewBinding: ItemGalleryBinding, position: Int) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(gallery.movies?.toMovieResultItem(onSelectItem)!!)
        }
        viewBinding.rvGalleryListItems.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = groupAdapter
        }

        viewBinding.tvGalleryTitle.text = gallery.name
    }

    override fun getLayout(): Int = R.layout.item_gallery

    override fun initializeViewBinding(view: View): ItemGalleryBinding {
        return ItemGalleryBinding.bind(view)
    }

    private fun List<MovieResultModel>.toMovieResultItem(onClick: (id: Int) -> Unit): List<MovieItem> {
        return this.map { MovieItem(it, onClick) }
    }
}