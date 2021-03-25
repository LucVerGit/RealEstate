package fr.LucasVerrier.realestatemanager.view.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import fr.LucasVerrier.realestatemanager.R
import fr.LucasVerrier.realestatemanager.databinding.CellPropertyBinding
import fr.LucasVerrier.realestatemanager.model.Property
import java.text.NumberFormat
import java.util.*


class PropertyListAdapter(
    private var propertyList: MutableList<Property>,
    private val listener: PropertyClickListener,
) : RecyclerView.Adapter<PropertyListAdapter.PropertyViewHolder>() {


    // interface
    interface PropertyClickListener {
        fun onPropertyClickListener(property: Property)
    }


    // functions
    fun setList(newList: List<Property>) {
        when {
            newList.isEmpty() && propertyList.isNotEmpty() -> {
                for (i in propertyList.lastIndex downTo 0) {
                    propertyList.removeAt(i)
                    notifyItemRemoved(i)
                }
                return
            }
            propertyList.isEmpty() -> {
                for (i in newList.indices) {
                    propertyList.add(newList[i])
                    notifyItemInserted(i)
                }
                return
            }
        }

        for (i in propertyList.lastIndex downTo 0) {
            if (!newList.contains(propertyList[i])) {
                propertyList.removeAt(i)
                notifyItemRemoved(i)
            }
        }

        for (i in newList.indices) {
            if ((!propertyList.contains(newList[i]))) {
                if (i > propertyList.lastIndex) {
                    propertyList.add(newList[i])
                    notifyItemInserted(propertyList.size)
                    continue
                }
                propertyList.add(i, newList[i])
                notifyItemInserted(i)
            }
        }
    }


    // overridden functions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = CellPropertyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PropertyViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = propertyList[position]
        holder.bind(property)
        holder.itemView.setOnClickListener {
            listener.onPropertyClickListener(property)
        }
    }

    override fun getItemCount(): Int = propertyList.count()


    // inner class
    class PropertyViewHolder(
        private val binding: CellPropertyBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(property: Property) {
            if (property.photoList.isNotEmpty()) { // to deal with database populating
                Glide.with(itemView)
                    .load(property.photoList[0].uri)
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(4)))
                    .into(binding.photoImageView)
            }

            binding.typeTextView.text =
                if (property.detail.propertyType != null) property.detail.propertyType.toString()
                    .toLowerCase(Locale.ROOT)
                    .capitalize(Locale.ROOT)
                else context.getString(R.string.not_provided)

            binding.cityTextView.text =
                property.address.city ?: context.getString(R.string.not_provided)

            binding.priceTextView.text =
                if (property.detail.price != null) NumberFormat.getCurrencyInstance(Locale.US)
                    .run {
                        maximumFractionDigits = 0
                        format(property.detail.price)
                    }
                else context.getString(R.string.not_provided)

            if (property.detail.saleTimeStamp != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.constraintLayout.foreground = ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_transparent_foreground
                    )
                }
                binding.soldImageView.visibility = VISIBLE
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.constraintLayout.foreground = null
                }
                binding.soldImageView.visibility = GONE
            }
        }
    }
}

