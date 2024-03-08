package com.eb.cvmaker.ui.Education

import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Repository.EducationAddRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EducationAddVM @Inject constructor(var repository: EducationAddRepository) :
    BaseVM<Education>(repository) {


}