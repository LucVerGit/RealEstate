package fr.LucasVerrier.realestatemanager.model

import android.content.Context
import androidx.core.util.Pair
import fr.LucasVerrier.realestatemanager.R
import fr.LucasVerrier.realestatemanager.database.AppDatabase
import fr.LucasVerrier.realestatemanager.utils.formatTimeStamp


import java.text.NumberFormat
import java.util.*

/**
 * Object representing a user cross-criteria search over [AppDatabase] available properties.
 */
class PropertySearch(
    var propertyType: PropertyType? = null,
    var city: String? = null,
    var priceRange: List<Float>? = null,
    var squareMetersRange: List<Float>? = null,
    var roomsRange: List<Float>? = null,
    var photoListSize: Float? = null,
    var entryDateRange: Pair<Long, Long>? = null,
    var saleDateRange: Pair<Long, Long>? = null,
    var poiTypeList: MutableList<PointOfInterestType>? = null,
    var realtor: Realtor? = null,
) {

    fun isOn(): Boolean {
        return propertyType != null
                || city != null
                || priceRange != null
                || squareMetersRange != null
                || roomsRange != null
                || photoListSize != null
                || entryDateRange != null
                || saleDateRange != null
                || poiTypeList != null
                || realtor != null
    }

    fun clear() {
        propertyType = null
        city = null
        priceRange = null
        squareMetersRange = null
        roomsRange = null
        photoListSize = null
        entryDateRange = null
        saleDateRange = null
        poiTypeList = null
        realtor = null
    }

    fun toString(context: Context): String {
        return StringBuilder().run {
            if (propertyType != null) {
                append("\n")
                append(context.getString(R.string.type))
                append(": ")
                append(propertyType.toString())
            }
            if (city != null) {
                append("\n")
                append(context.getString(R.string.city))
                append(": ")
                append(city)
            }
            priceRange?.let { list ->
                append("\n")
                append(context.getString(R.string.price_range))
                append(": ")
                append(
                    context.getString(
                        R.string.range_result,
                        NumberFormat.getCurrencyInstance(Locale.US).run {
                            maximumFractionDigits = 0
                            format(list[0].toDouble())
                        },
                        NumberFormat.getCurrencyInstance(Locale.US).run {
                            maximumFractionDigits = 0
                            format(list[1].toDouble())
                        })
                )
            }
            squareMetersRange?.let { list ->
                append("\n")
                append(context.getString(R.string.surface_range))
                append(": ")
                append(
                    context.getString(
                        R.string.range_result,
                        NumberFormat.getInstance(Locale.ROOT).run {
                            maximumFractionDigits = 0
                            format(list[0])
                        },
                        NumberFormat.getInstance(Locale.ROOT).run {
                            maximumFractionDigits = 0
                            format(list[1])
                        })
                )
                append("m²")
            }
            roomsRange?.let { list ->
                append("\n")
                append(context.getString(R.string.rooms_range))
                append(": ")
                append(
                    context.getString(
                        R.string.range_result,
                        NumberFormat.getInstance(Locale.ROOT).run {
                            maximumFractionDigits = 0
                            format(list[0])
                        },
                        NumberFormat.getInstance(Locale.ROOT).run {
                            maximumFractionDigits = 0
                            format(list[1])
                        })
                )
            }
            if (photoListSize != null) {
                append("\n")
                append(context.getString(R.string.minimum_photos))
                append(": ")
                append(NumberFormat.getInstance(Locale.ROOT).run {
                    maximumFractionDigits = 0
                    format(photoListSize)
                })
            }
            entryDateRange?.let { pair ->
                append("\n")
                append(context.getString(R.string.entry_date_range))
                append(": ")
                append(
                    context.getString(
                        R.string.range_result,
                        formatTimeStamp(pair.first!!),
                        formatTimeStamp(pair.second!!)
                    )
                )
            }
            saleDateRange?.let { pair ->
                append("\n")
                append(context.getString(R.string.sale_date_range))
                append(": ")
                append(
                    context.getString(
                        R.string.range_result,
                        formatTimeStamp(pair.first!!),
                        formatTimeStamp(pair.second!!)
                    )
                )
            }
            if (poiTypeList != null) {
                append("\n")
                append(context.getString(R.string.points_of_interest))
                append(": ")
                for (i in 0..poiTypeList!!.lastIndex) {
                    append("${poiTypeList?.get(i)}")
                    if (i != poiTypeList!!.lastIndex) append(", ")
                }
            }
            if (realtor != null) {
                append("\n")
                append(context.getString(R.string.realtor))
                append(": ")
                append(realtor.toString())
            }

            toString().trim()
        }
    }
}