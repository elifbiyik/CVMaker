package com.eb.cvmaker.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Model.References
import com.eb.cvmaker.Model.UserInfo
import com.eb.cvmaker.Repository.EducationRepository
import com.eb.cvmaker.Repository.ReferencesRepository
import com.eb.cvmaker.Repository.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VM @Inject constructor(var repository: EducationRepository) : BaseVM<Education>(repository) {

    var allMLD = MutableLiveData<List<Education>>()

    init {
        getAll()
    }

    fun getAll() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getUserEducationInfo()
            userInfoMLD.value = response
        }
    }


}