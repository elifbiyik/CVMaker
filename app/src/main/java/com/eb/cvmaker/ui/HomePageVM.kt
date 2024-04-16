package com.eb.cvmaker.ui

import androidx.lifecycle.ViewModel
import com.eb.cvmaker.Repository.HomePageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageVM @Inject constructor(var repository: HomePageRepository) :
    ViewModel() {

}