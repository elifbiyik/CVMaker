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
class ChooseTemplateViewModel @Inject constructor(var repository: ChooseTemplateRepository) :
    ViewModel() {

    var communicationMLD = MutableLiveData<List<Communication>>()
    var educationMLD = MutableLiveData<List<Education>>()
    var socialMediaMLD = MutableLiveData<List<SocialMedia>>()
    var experienceMLD = MutableLiveData<List<Experience>>()
    var languageMLD = MutableLiveData<List<Languages>>()
    var certificateMLD = MutableLiveData<List<Certificates>>()
    var abilitiesMLD = MutableLiveData<List<Abilities>>()
    var referencesMLD = MutableLiveData<List<References>>()


    init {
        getCommunication()
        getEducation()
        getSocialMedia()
        getExperience()
        getLanguage()
        getCertificates()
        getAbilities()
        getReferences()
    }


    suspend fun getTemplate(): ArrayList<Uri> {
        var response = repository.getAllTemplate()
        return response
    }

     fun getCommunication() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getCommunication()
            communicationMLD.value = response
        }
    }

    fun getEducation() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getEducation()
            educationMLD.value = response
        }
    }

    fun getSocialMedia() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getSocialMedia()
            socialMediaMLD.value = response
        }
    }

    fun getExperience() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getExperience()
            experienceMLD.value = response
        }
    }

    fun getLanguage() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getLanguage()
            languageMLD.value = response
        }
    }

    fun getCertificates() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getCertificates()
            certificateMLD.value = response
        }
    }

    fun getAbilities() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getAbilities()
            abilitiesMLD.value = response
        }
    }

    fun getReferences() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getReferences()
            referencesMLD.value = response
        }
    }



}