package com.farzin.imdb.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farzin.imdb.utils.Constants

@Entity(tableName = Constants.MEDIA_TABLE_NAME)
data class FavoriteDBModel(
    @PrimaryKey
    val id:Int = 0,
    val name:String,
    val year:String,
    val image:String,
    val rating:Double,
    val isMovie:Boolean
)
