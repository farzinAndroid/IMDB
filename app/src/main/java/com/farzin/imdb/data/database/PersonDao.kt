package com.farzin.imdb.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farzin.imdb.models.database.PersonDBModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPerson(person: PersonDBModel)

    @Query("select * from person_table order by id desc")
    fun getAllPersons() : Flow<List<PersonDBModel>>

    @Delete
    suspend fun removePerson(person: PersonDBModel)


    @Query("select * from person_table where id=:id")
    fun getId(id: Int) : Int

}