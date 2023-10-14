package com.farzin.imdb.di

import android.content.Context
import com.farzin.imdb.data.datastore.DataStoreRepo
import com.farzin.imdb.data.datastore.DataStoreRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStoreImpl(
        @ApplicationContext context: Context
    ) : DataStoreRepoImpl = DataStoreRepoImpl(context)

}