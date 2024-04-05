package com.eb.cvmaker.ui._Create

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.Model.Communication
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.Model.Languages
import com.eb.cvmaker.Model.References
import com.eb.cvmaker.Model.SocialMedia
import com.eb.cvmaker.Repository.ChooseTemplateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChooseTemplateVM @Inject constructor(var repository: ChooseTemplateRepository) :
    ViewModel() {

    init {
        getData()
    }

    var communicationMLD = MutableLiveData<List<Communication>>()
    var educationMLD = MutableLiveData<List<Education>>()
    var socialMediaMLD = MutableLiveData<List<SocialMedia>>()
    var experienceMLD = MutableLiveData<List<Experience>>()
    var languageMLD = MutableLiveData<List<Languages>>()
    var certificateMLD = MutableLiveData<List<Certificates>>()
    var abilitiesMLD = MutableLiveData<List<Abilities>>()
    var referencesMLD = MutableLiveData<List<References>>()

    suspend fun getTemplate(): ArrayList<Uri> {
        var response = repository.getAllTemplate()
        return response
    }
    private fun getData() {
        viewModelScope.launch(Dispatchers.Main) {
            communicationMLD.value = repository.getCommunication()
            educationMLD.value = repository.getEducation()
            socialMediaMLD.value = repository.getSocialMedia()
            experienceMLD.value = repository.getExperience()
            languageMLD.value = repository.getLanguage()
            certificateMLD.value = repository.getCertificates()
            abilitiesMLD.value = repository.getAbilities()
            referencesMLD.value = repository.getReferences()
        }
    }

}