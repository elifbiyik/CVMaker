package com.eb.cvmaker.ui.SocialMedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eb.cvmaker.Model.SocialMedia
import com.eb.cvmaker.Repository.SocialMediaRepository
import com.eb.cvmaker.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialMediaVM @Inject constructor(var repository : SocialMediaRepository) : BaseVM<SocialMedia> (repository) {

    override var userInfoMLD = MutableLiveData<List<SocialMedia>>()

    init {
        getUserSocialMediaInfo()
    }

    fun getUserSocialMediaInfo () {
        viewModelScope.launch(Dispatchers.Main) {
            var response = repository.getUserSocialMediaInfo()
            userInfoMLD.value = response
        }
    }

}