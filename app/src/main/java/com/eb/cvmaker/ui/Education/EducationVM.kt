package com.eb.cvmaker.ui.Education

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Repository.BaseRepository
import com.eb.cvmaker.Repository.EducationRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EducationVM @Inject constructor(var repository: EducationRepository): BaseVM<Education>(repository) {

    override var userInfoMLD = MutableLiveData<List<Education>>()

    init {
        getUserEducationInfo()
    }

    fun getUserEducationInfo () {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getUserEducationInfo()
            userInfoMLD.value = response
        }
    }
}