package com.eb.cvmaker.Repository

import android.net.Uri
import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.Model.Communication
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.Model.Languages
import com.eb.cvmaker.Model.References
import com.eb.cvmaker.Model.SocialMedia
import com.eb.cvmaker.dB.AbilitiesDao
import com.eb.cvmaker.dB.CertificatesDao
import com.eb.cvmaker.dB.CommunicationDao
import com.eb.cvmaker.dB.EducationDao
import com.eb.cvmaker.dB.ExperienceDao
import com.eb.cvmaker.dB.LanguageDao
import com.eb.cvmaker.dB.ReferencesDao
import com.eb.cvmaker.dB.SocialMediaDao
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class ChooseTemplateRepository @Inject constructor(var storage: FirebaseStorage, var auth: FirebaseAuth) {

    @Inject
    lateinit var communicationDao: CommunicationDao

    @Inject
    lateinit var educationDao: EducationDao

    @Inject
    lateinit var socialMediaDao: SocialMediaDao

    @Inject
    lateinit var experienceDao: ExperienceDao

    @Inject
    lateinit var languageDao: LanguageDao

    @Inject
    lateinit var certificatesDao: CertificatesDao

    @Inject
    lateinit var abilitiesDao: AbilitiesDao

    @Inject
    lateinit var referencesDao: ReferencesDao


    suspend fun getAllTemplate(): ArrayList<Uri> {
        val list: ArrayList<Uri> = ArrayList()

        val storageReference = storage.reference.listAll().await()
        try {
            for (item in storageReference.items) {
                var download = item.downloadUrl.await()
                list.add(download)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

   suspend fun getCommunication(): List<Communication> {
        return communicationDao.getAllInfo()
    }

    suspend fun getEducation(): List<Education> {
        return educationDao.getAllInfo()
    }

    suspend fun getSocialMedia(): List<SocialMedia> {
        return socialMediaDao.getAllInfo()
    }

    suspend fun getExperience(): List<Experience> {
        return experienceDao.getAllInfo()
    }

    suspend fun getLanguage(): List<Languages> {
        return languageDao.getAllInfo()
    }

    suspend fun getCertificates(): List<Certificates> {
        return certificatesDao.getAllInfo()
    }

    suspend fun getAbilities(): List<Abilities> {
        return abilitiesDao.getAllInfo()
    }
    suspend fun getReferences(): List<References> {
        return referencesDao.getAllInfo()
    }

    fun getProfile(): Task<Uri> {
        val imageReference = auth.currentUser?.let { currentUser ->
            val uid = currentUser.uid
            FirebaseStorage.getInstance().reference.child("images/$uid.jpg")
        }

        return imageReference?.downloadUrl
            ?: Tasks.forException(NullPointerException("Current user is null"))

    }
}