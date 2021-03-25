package fr.LucasVerrier.realestatemanager.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.LucasVerrier.realestatemanager.databinding.CellAddPointOfInterestBinding
import fr.LucasVerrier.realestatemanager.model.PointOfInterest

class AddPointOfInterestListAdapter(
    var pointOfInterestList: MutableList<PointOfInterest>,
    private val onDeletePointOfInterestListener: OnDeletePointOfInterestListener
) : RecyclerView.Adapter<AddPointOfInterestListAdapter.PointOfInterestViewHolder>() {


    // interfaces
    interface OnDeletePointOfInterestListener {
        fun onDeletePointOfInterest(pointOfInterest: PointOfInterest)
    }


    // overridden functions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointOfInterestViewHolder {
        val view = CellAddPointOfInterestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PointOfInterestViewHolder(view, onDeletePointOfInterestListener)
    }

    override fun onBindViewHolder(holder: PointOfInterestViewHolder, position: Int) =
        holder.bind(pointOfInterestList[position])

    override fun getItemCount(): Int = pointOfInterestList.count()


    // inner class
    class PointOfInterestViewHolder(
        private val binding: CellAddPointOfInterestBinding,
        private val onDeletePointOfInterestListener: OnDeletePointOfInterestListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pointOfInterest: PointOfInterest) {
            binding.pointOfInterest.text = pointOfInterest.toString()
            binding.deleteButton.setOnClickListener {
                onDeletePointOfInterestListener.onDeletePointOfInterest(pointOfInterest)
            }
        }
    }
}

