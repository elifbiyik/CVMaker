package com.eb.cvmaker.ui.References

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Model.References
import com.eb.cvmaker.Repository.ReferencesRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReferencesVM @Inject constructor(var repository: ReferencesRepository): BaseVM<References>(repository) {

    override var userInfoMLD = MutableLiveData<List<References>>()

    init {
        getUserCertificatesInfo()
    }

    fun getUserCertificatesInfo () {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getUserCertificatesInfo()
            userInfoMLD.value = response
        }
    }
}