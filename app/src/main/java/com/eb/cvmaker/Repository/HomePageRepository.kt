package com.eb.cvmaker.Repository

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class HomePageRepository @Inject constructor(var storage: FirebaseStorage) {


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
}