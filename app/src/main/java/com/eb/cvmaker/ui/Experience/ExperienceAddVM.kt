package com.eb.cvmaker.ui.Experience

import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.Repository.ExperienceAddRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExperienceAddVM @Inject constructor(var repository: ExperienceAddRepository) :
    BaseVM<Experience>(repository) {

}