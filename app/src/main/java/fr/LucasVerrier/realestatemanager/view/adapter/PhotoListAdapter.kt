package fr.LucasVerrier.realestatemanager.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import fr.LucasVerrier.realestatemanager.databinding.CellPhotoBinding
import fr.LucasVerrier.realestatemanager.model.Photo

class PhotoListAdapter(
    photoList: MutableList<Photo>,
    private val onPhotoClickListener: OnPhotoClickListener
) :
    RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    // interfaces
    interface OnPhotoClickListener {
        fun onPhotoClick(photo: Photo)
    }


    // variables
    var photoList: MutableList<Photo> = photoList
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    // overridden functions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = CellPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.run {
            bind(photoList[position])
            itemView.setOnClickListener {
                onPhotoClickListener.onPhotoClick(photoList[position])
            }
        }
    }

    override fun getItemCount(): Int = photoList.count()


    // inner class
    class PhotoViewHolder(private val binding: CellPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            Glide.with(itemView)
                .load(photo.uri)
                .apply(
                    RequestOptions().transform(
                        CenterCrop(),
                        GranularRoundedCorners(4f, 4f, 0f, 0f)
                    )
                )
                .into(binding.photoImageView)

            binding.photoTitleTextView.text = photo.title
        }
    }
}

