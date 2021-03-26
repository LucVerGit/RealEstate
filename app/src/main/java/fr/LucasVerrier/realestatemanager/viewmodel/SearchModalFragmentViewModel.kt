package fr.LucasVerrier.realestatemanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import fr.LucasVerrier.realestatemanager.model.MinMax
import fr.LucasVerrier.realestatemanager.model.Realtor
import fr.LucasVerrier.realestatemanager.repository.PropertyRepository

class SearchModalFragmentViewModel(private val propertyRepository: PropertyRepository) :
    ViewModel() {

    // variables
    val realtorList: LiveData<List<Realtor>> = propertyRepository.realtorList.asLiveData()
    val cityList: LiveData<List<String>> = propertyRepository.cityList.asLiveData()


    // functions
    fun getPriceBounds(): LiveData<MinMax> {
        return propertyRepository.getPriceBounds()
    }

    fun getSquareMetersBounds(): LiveData<MinMax> {
        return propertyRepository.getSquareMetersBounds()
    }

    fun getRoomsBounds(): LiveData<MinMax> {
        return propertyRepository.getRoomsBounds()
    }

    fun getPhotoListMax(): LiveData<Int> {
        return propertyRepository.getPhotoListMax()
    }
}

class SearchModalFragmentViewModelFactory(private val propertyRepository: PropertyRepository) :
    ViewModelProvider.Factory {

    // overridden functions
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchModalFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchModalFragmentViewModel(propertyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}