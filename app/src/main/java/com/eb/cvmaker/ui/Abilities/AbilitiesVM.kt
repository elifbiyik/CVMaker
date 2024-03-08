package com.eb.cvmaker.ui.Abilities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Repository.AbilitiesRepository
import com.eb.cvmaker.Repository.BaseRepository
import com.eb.cvmaker.Repository.EducationRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AbilitiesVM @Inject constructor(var repository: AbilitiesRepository): BaseVM<Abilities>(repository) {

    override var userInfoMLD = MutableLiveData<List<Abilities>>()

    init {
        getUserEducationInfo()
    }

    fun getUserEducationInfo () {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getUserAbilitiesInfo()
            userInfoMLD.value = response
        }
    }
}