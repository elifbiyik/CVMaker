package com.eb.cvmaker.ui.Experience

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.Repository.ExperienceRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExperienceVM @Inject constructor(var repository: ExperienceRepository): BaseVM<Experience> (repository) {

    override var userInfoMLD = MutableLiveData<List<Experience>>()

    init {
        getUserExperienceInfo()
    }

    fun getUserExperienceInfo () {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getUserExperienceInfo()
            userInfoMLD.value = response
        }
    }
}