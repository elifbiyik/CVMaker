package com.eb.cvmaker.ui.Language

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Model.Languages
import com.eb.cvmaker.Repository.LanguagesRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageVM @Inject constructor(var repository: LanguagesRepository) :
    BaseVM<Languages>(repository) {

   override var userInfoMLD = MutableLiveData<List<Languages>>()

    init {
        getUserLanguagesInfo()
    }

    fun getUserLanguagesInfo() {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getUserLanguagesInfo()
            userInfoMLD.value = response
        }
    }


}