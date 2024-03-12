package com.eb.cvmaker.dB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.Model.Communication
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.Model.Languages
import com.eb.cvmaker.Model.References
import com.eb.cvmaker.Model.SocialMedia

@Database(
    entities = [Abilities::class, Certificates::class, Communication::class, Education::class, Experience::class, Languages::class, References::class, SocialMedia::class],
    version = 5
)
abstract class UserInfoDatabase : RoomDatabase() {
    abstract fun abilitiesDao(): AbilitiesDao
    abstract fun certificatesDao(): CertificatesDao
    abstract fun communicationDao(): CommunicationDao
    abstract fun educationDao(): EducationDao
    abstract fun experienceDao(): ExperienceDao
    abstract fun languageDao(): LanguageDao
    abstract fun referencesDao(): ReferencesDao
    abstract fun socialMediaDao(): SocialMediaDao
}