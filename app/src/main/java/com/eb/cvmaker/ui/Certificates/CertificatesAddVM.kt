package com.eb.cvmaker.ui.Certificates

import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.Repository.CertificatesAddRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CertificatesAddVM @Inject constructor(private var repository: CertificatesAddRepository) :
    BaseVM<Certificates>(repository) {

}