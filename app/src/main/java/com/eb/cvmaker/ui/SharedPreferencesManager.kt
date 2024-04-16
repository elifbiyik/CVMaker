package com.eb.cvmaker.ui

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    private val PDF_PATH_KEY = "pdfFilePath"
    private val USER_IMAGE_KEY = "userImage"
    private val LANGUAGE_KEY = "language"

    fun savePdfPath(path: String) {
        sharedPreferences.edit().putString(PDF_PATH_KEY, path).apply()
    }

    fun getPdfPath(): String? {
        return sharedPreferences.getString(PDF_PATH_KEY, null)
    }

    fun saveUserImage(imagePath: String) {
        sharedPreferences.edit().putString(USER_IMAGE_KEY, imagePath).apply()
    }

    fun getUserImage(): String? {
        return sharedPreferences.getString(USER_IMAGE_KEY, null)
    }

    fun saveLanguage(language: String) {
        sharedPreferences.edit().putString(LANGUAGE_KEY, language).apply()
    }

    fun getLanguage(): String? {
        return sharedPreferences.getString(LANGUAGE_KEY, null)
    }
}
