package com.eb.cvmaker.di

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.eb.cvmaker.dB.AbilitiesDao
import com.eb.cvmaker.dB.CertificatesDao
import com.eb.cvmaker.dB.CommunicationDao
import com.eb.cvmaker.dB.EducationDao
import com.eb.cvmaker.dB.ExperienceDao
import com.eb.cvmaker.dB.LanguageDao
import com.eb.cvmaker.dB.ReferencesDao
import com.eb.cvmaker.dB.SocialMediaDao
import com.eb.cvmaker.dB.UserInfoDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): UserInfoDatabase {
        return Room.databaseBuilder(context, UserInfoDatabase::class.java, "CV.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    // databaseBuilder ->  yapılandırma seçenkeleri
// fallbackToDestructiveMigration -> yapılacak değişikliklerle ilgili. Her şema değişikliğinde eski veriler silinir ve baştan yapılır.

    // Uygulamada veritabanı işlemleri yapmak için kullanılır.
    // Context kullanabilmek için @ApplicationContext context : Context yazılmak zorunda!!

    // AppDatabase::class.java -> abstract sınıf veya interface olmalıdır.
// CV.db -> Oluşturulan veritabanının adıdır.( SQLite veritabanı dosyasının adı )
    // uygulama içinde CV.db ile erişilir.

/*
    @Provides
    @Singleton
    fun provideUserDao(database: UserInfoDatabase): UserInfoDao = database.userInfoDao()
*/


    @Provides
    @Singleton
    fun provideCommunicationDao(appDatabase: UserInfoDatabase): CommunicationDao = appDatabase.communicationDao()

    @Provides
    @Singleton
    fun provideAbilitiesDao(appDatabase: UserInfoDatabase): AbilitiesDao = appDatabase.abilitiesDao()

    @Provides
    @Singleton
    fun provideCertificatesDao(appDatabase: UserInfoDatabase): CertificatesDao = appDatabase.certificatesDao()

    @Provides
    @Singleton
    fun provideEducationDao(appDatabase: UserInfoDatabase): EducationDao = appDatabase.educationDao()

    @Provides
    @Singleton
    fun provideExperienceDao(appDatabase: UserInfoDatabase): ExperienceDao = appDatabase.experienceDao()

    @Provides
    @Singleton
    fun provideLanguageDao(appDatabase: UserInfoDatabase): LanguageDao = appDatabase.languageDao()

    @Provides
    @Singleton
    fun provideReferencesDao(appDatabase: UserInfoDatabase): ReferencesDao = appDatabase.referencesDao()

    @Provides
    @Singleton
    fun provideSocialMediaDao(appDatabase: UserInfoDatabase): SocialMediaDao = appDatabase.socialMediaDao()


    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

}