package com.karim.framemedia.presentation.feature_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.karim.framemedia.R
import com.karim.framemedia.databinding.FragmentListBinding
import com.karim.framemedia.presentation.feature_details.DetailsFragment.Companion.DATA_ARGUMENT_AUTHOR
import com.karim.framemedia.presentation.feature_details.DetailsFragment.Companion.DATA_ARGUMENT_DESC
import com.karim.framemedia.presentation.feature_details.DetailsFragment.Companion.DATA_ARGUMENT_IMAGE
import com.karim.framemedia.presentation.feature_details.DetailsFragment.Companion.DATA_ARGUMENT_TITLE
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ArtworksFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ArtworksViewModel by inject()
    private lateinit var adapter: ArtworksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ArtworksAdapter {
            findNavController().navigate(
                R.id.action_artworksFragment_to_detailsFragment,
                bundleOf(
                    DATA_ARGUMENT_IMAGE to it.imageId,
                    DATA_ARGUMENT_TITLE to it.title,
                    DATA_ARGUMENT_DESC to it.description,
                    DATA_ARGUMENT_AUTHOR to it.authorName,
                )
            )
        }
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            viewModel.moviesList.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}