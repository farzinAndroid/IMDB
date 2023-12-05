package com.farzin.imdb.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farzin.imdb.utils.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class PersonDBModel(
    @PrimaryKey
    val id:Int = 0,
    val name:String,
    val job:String,
    val image:String
)
