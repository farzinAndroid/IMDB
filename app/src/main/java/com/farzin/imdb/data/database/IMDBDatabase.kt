package com.farzin.imdb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.farzin.imdb.models.database.PersonDBModel

@Database(entities = [PersonDBModel::class], version = 1, exportSchema = false)
abstract class IMDBDatabase : RoomDatabase() {

    abstract fun getPersonDao() : PersonDao

}