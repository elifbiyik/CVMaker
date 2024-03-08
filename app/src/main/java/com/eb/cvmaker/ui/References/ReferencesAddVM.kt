package com.eb.cvmaker.ui.References

import com.eb.cvmaker.Model.References
import com.eb.cvmaker.Repository.ReferencesAddRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReferencesAddVM @Inject constructor(var repository: ReferencesAddRepository) :
    BaseVM<References>(repository) {

}
