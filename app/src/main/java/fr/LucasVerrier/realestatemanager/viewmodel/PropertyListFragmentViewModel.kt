package fr.LucasVerrier.realestatemanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import fr.LucasVerrier.realestatemanager.model.Property
import fr.LucasVerrier.realestatemanager.model.PropertySearch
import fr.LucasVerrier.realestatemanager.repository.PropertyRepository

class PropertyListFragmentViewModel(private val propertyRepository: PropertyRepository) :
    ViewModel() {

    // functions
    fun getPropertyFilterableList(propertySearch: PropertySearch): LiveData<List<Property>> {
        return propertyRepository.getPropertyFilterableList(propertySearch).asLiveData()
    }
}

class PropertyListFragmentViewModelFactory(private val propertyRepository: PropertyRepository) :
    ViewModelProvider.Factory {

    // overridden functions
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PropertyListFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PropertyListFragmentViewModel(propertyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}