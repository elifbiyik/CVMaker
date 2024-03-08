package com.eb.cvmaker.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseVM<T> @Inject constructor(private val repository: BaseRepository<T>) :
    ViewModel() {

    open var userInfoMLD = MutableLiveData<List<T>>()
    var isSuccessfulMLD = MutableLiveData<Boolean>()

/*
    init {
        getAllData()
    }
*/

    fun saveData(userData: T) {
        viewModelScope.launch(Dispatchers.Main) {
            val isSuccessful = repository.saveData(userData)
            isSuccessfulMLD.value = isSuccessful
        }
    }

    fun deleteData(userData: T) {
        viewModelScope.launch(Dispatchers.Main) {
            val isSuccessful = repository.deleteData(userData)
            isSuccessfulMLD.value = isSuccessful

            userInfoMLD
        }
    }

    fun updateData(userData: T) {
        viewModelScope.launch(Dispatchers.Main) {
            val isSuccessful = repository.updateData(userData)
            isSuccessfulMLD.value = isSuccessful
        }
    }
/*
    fun getAllData() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = repository.getData()
            userInfoMLD.value = response
        }
    }
    */

}