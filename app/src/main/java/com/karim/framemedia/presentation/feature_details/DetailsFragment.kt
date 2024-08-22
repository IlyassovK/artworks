package com.karim.framemedia.presentation.feature_details

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karim.framemedia.R
import com.karim.framemedia.databinding.FragmentDetailsBinding
import com.karim.framemedia.domain.Artwork
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    companion object {
        const val DATA_ARGUMENT_IMAGE = "details_data_image"
        const val DATA_ARGUMENT_TITLE = "details_data_title"
        const val DATA_ARGUMENT_DESC = "details_data_desc"
        const val DATA_ARGUMENT_AUTHOR = "details_data_author"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            arguments.apply {
                arguments?.getString(DATA_ARGUMENT_TITLE)?.let {
                    title.text = it
                    toolbar.title = it
                }
                arguments?.getString(DATA_ARGUMENT_DESC)
                    ?.let { description.text = Html.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
                arguments?.getString(DATA_ARGUMENT_AUTHOR)?.let { author.text = it }
                arguments?.getString(DATA_ARGUMENT_IMAGE)?.let { loadImage(it) }
            }
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    private fun loadImage(imageId: String?) {
        if (imageId == null) {
            binding.image.setImageResource(R.drawable.placeholder)
            return
        }

        val link = "https://www.artic.edu/iiif/2/$imageId/full/200,/0/default.jpg"
        Picasso.get()
            .load(link)
            .placeholder(R.drawable.progress_animation)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(binding.image)
    }
}