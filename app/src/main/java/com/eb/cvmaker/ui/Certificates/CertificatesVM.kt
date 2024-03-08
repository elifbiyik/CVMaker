package com.eb.cvmaker.ui.Certificates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.Repository.CertificatesRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CertificatesVM @Inject constructor(var repository: CertificatesRepository): BaseVM<Certificates>(repository) {

    override var userInfoMLD = MutableLiveData<List<Certificates>>()

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
