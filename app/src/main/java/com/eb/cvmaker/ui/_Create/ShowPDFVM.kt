package com.eb.cvmaker.ui._Create

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowPDFVM @Inject constructor() : ViewModel() {


    var pdfFilePath: String? = null



}