package com.eb.cvmaker.ui.Communication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Model.Communication
import com.eb.cvmaker.Repository.CommunicationRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunicationVM @Inject constructor(private var repository: CommunicationRepository) :
    BaseVM<Communication>(repository) {

    override var userInfoMLD = MutableLiveData<List<Communication>>()

    init {
        getUserCommunicationInfo()
    }

    fun getUserCommunicationInfo() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getUserCommunicationInfo()
            userInfoMLD.value = response
        }
    }
}