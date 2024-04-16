package com.eb.cvmaker.Model

import com.eb.cvmaker.R

data class AppLanguage(

    var id: Int = 0,
    var code: String? = null,
    var languageName: String? = null,
    var flag: Int? = null
) {

    companion object {
        val listAppLanguages = listOf(
            AppLanguage(1, "tr", "Türkçe", R.drawable.flag_tr),
            AppLanguage(2, "en", "English (UK)", R.drawable.flag_uk)
        )
    }
}