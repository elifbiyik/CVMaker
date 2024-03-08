package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
@Embedded annotasyonu: bir entity içinde başka bir entity'i temsil etmek için kullanılır.
Ancak, @Embedded annotasyonunu kullanırken, iç içe geçmiş entity'nin alanları,
ana entity'nin alanlarıyla aynı isme sahip olursa çakışma olabilir.
Bu durumu önlemek için @Embedded annotasyonu ile birlikte prefix parametresi kullanılabilir.
 */

/*
UserInfo entity'si içinde Communication entity'si @Embedded kullanılarak temsil edilmektedir:
 */

/*
Örneğin, Education entity'sinde bir alan schoolName olarak tanımlıysa,
bu, UserInfo entity'sinde education_schoolName olarak görülecektir.
 */

@Entity(tableName = "UserAllInfo")
data class UserInfo(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @Embedded(prefix = "communication_")
    var communication: Communication? = null,

    @Embedded(prefix = "education_")
    var education: Education ?= null,

    @Embedded(prefix = "experience_")
    var experience: Experience ?= null,

    @Embedded(prefix = "abilities_")
    var abilities: Abilities ?= null,

    @Embedded(prefix = "languages_")
    var languages: Languages ?= null,

    @Embedded(prefix = "certificates_")
    var certificates: Certificates ?= null,

    @Embedded(prefix = "references_")
    var references: References ?= null ,

)
