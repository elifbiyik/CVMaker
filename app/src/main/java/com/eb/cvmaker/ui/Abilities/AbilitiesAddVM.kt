package com.eb.cvmaker.ui.Abilities

import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.Repository.AbilitiesRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AbilitiesAddVM @Inject constructor(private var repository: AbilitiesRepository) :
    BaseVM<Abilities>(repository) {

}