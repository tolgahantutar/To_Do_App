package com.tolgahantutar.to_do_app.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import javax.inject.Inject

@Entity
data class Note @Inject constructor(
    val title: String,
    val duration: String,
    val price: Double,
    val note: String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var Id: Int = 0
}