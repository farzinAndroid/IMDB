package com.farzin.imdb.di

import android.content.Context
import androidx.room.Room
import com.farzin.imdb.data.database.IMDBDatabase
import com.farzin.imdb.data.database.PersonDao
import com.farzin.imdb.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext c: Context) = Room.databaseBuilder(
        c,
        IMDBDatabase::class.java,
        Constants.DB_NAME
    ).build()


    @Provides
    fun providePersonDaoModule(database:IMDBDatabase) : PersonDao = database.getPersonDao()

}