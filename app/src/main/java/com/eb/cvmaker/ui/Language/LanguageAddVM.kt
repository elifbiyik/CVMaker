package com.eb.cvmaker.ui.Language

import com.eb.cvmaker.Model.Languages
import com.eb.cvmaker.Repository.LanguagesAddRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageAddVM @Inject constructor(var repository: LanguagesAddRepository) :
    BaseVM<Languages>(repository) {

}
