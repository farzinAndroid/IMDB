package com.farzin.imdb.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.farzin.imdb.models.database.FavoriteDBModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("select * from favorite_table")
    fun getAllFavorites() : Flow<List<FavoriteDBModel>>

    @Insert
    suspend fun addFavorite(favoriteDBModel: FavoriteDBModel)

    @Delete
    suspend fun removeFavorite(favoriteDBModel: FavoriteDBModel)

    @Query("select * from favorite_table where id=:id")
    fun getId(id:Int) : Int

}