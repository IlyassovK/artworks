package com.karim.framemedia.presentation.feature_list

import android.text.Html.fromHtml
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karim.framemedia.R
import com.karim.framemedia.databinding.ListItemViewBinding
import com.karim.framemedia.domain.Artwork
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class ArtworksAdapter(private val onClick: (item: Artwork) -> Unit) :
    PagingDataAdapter<Artwork, ArtworksAdapter.ArtworksViewHolder>(ArtworksDiffUtil) {

    inner class ArtworksViewHolder(private val binding: ListItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Artwork) {
            binding.apply {
                title.text = data.title
                description.text = fromHtml(data.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                loadImage(binding.image, data.imageId)
                root.setOnClickListener {
                    onClick.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworksViewHolder {
        val binding =
            ListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtworksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtworksViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    private fun loadImage(imageView: ImageView, imageId: String?) {
        if (imageId == null) {
            imageView.setImageResource(R.drawable.placeholder)
            return
        }

        val link = "https://www.artic.edu/iiif/2/$imageId/full/200,/0/default.jpg"
        Picasso.get()
            .load(link)
            .placeholder(R.drawable.progress_animation)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .into(imageView)
    }
}

private object ArtworksDiffUtil : DiffUtil.ItemCallback<Artwork>() {
    override fun areItemsTheSame(oldItem: Artwork, newItem: Artwork) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Artwork, newItem: Artwork) =
        oldItem.title == newItem.title
}