package com.lorenzofonseca.filmify.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lorenzofonseca.filmify.databinding.FragmentHomeBinding
import com.lorenzofonseca.filmify.utils.invisible
import com.lorenzofonseca.filmify.utils.subscribe
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), LifecycleOwner {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var groupAdapter: GroupAdapter<GroupieViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        groupAdapter = GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater).apply {
        viewModel = this@HomeFragment.viewModel
        lifecycleOwner = this@HomeFragment
    }.root

    override fun onResume() {
        super.onResume()
        groupAdapter.clear()
        viewModel.getDiscoverPage()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeEvents()
    }

    private fun subscribeEvents() {
        viewModel.onGetDiscoverSuccess.subscribe(this, {

            groupAdapter.add(GalleryItem(it, ::onSelectItem))

            binding.rvGalleryList.apply {
                setHasFixedSize(true)
                adapter = groupAdapter
                layoutManager = LinearLayoutManager(context)
            }

            binding.shPlaceHolder.invisible()
        })

    }

    private fun onSelectItem(id: Int) {
        findNavController().navigate(HomeFragmentDirections.goToMovieDetailsFragment(id))
    }
}