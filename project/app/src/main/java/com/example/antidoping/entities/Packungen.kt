package com.example.antidoping.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "Packungen",
    primaryKeys = arrayOf("MediUid", "PZN"),
    foreignKeys = [
        ForeignKey(
            entity = Medis::class,
            parentColumns = ["Uid"],
            childColumns = ["MediUid"],
            onDelete = CASCADE
        )
    ]
)
data class Packungen(
    @ColumnInfo(name = "PZN")
    val pzn: Int,
    @NonNull @ColumnInfo(name = "MediUid")
    val medicineId: Int,
    @ColumnInfo(name = "Frei")
    val free: Int?

    /**
     * The "Pharmazentralnummer" (Pharmacy Central Number).
     * This integer is the code that gets scanned from a barcode.
     *
     * See [https://confluence.allaboutapps.at/pages/viewpage.action?pageId=71795254].
     */

)