package com.farzin.imdb.di

import com.farzin.imdb.data.remote.HomeApiInterface
import com.farzin.imdb.data.remote.MovieDetailApiInterface
import com.farzin.imdb.data.remote.TVDetailApiInterface
import com.farzin.imdb.data.remote.ProfileApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiInterfacesModule {

    @Provides
    @Singleton
    fun provideHomeApiInterface(retrofit: Retrofit) : HomeApiInterface =
        retrofit.create(HomeApiInterface::class.java)


    @Provides
    @Singleton
    fun provideSignInApiInterface(retrofit: Retrofit) : ProfileApiInterface =
        retrofit.create(ProfileApiInterface::class.java)


    @Provides
    @Singleton
    fun provideTVDetailApiInterface(retrofit: Retrofit) : TVDetailApiInterface =
        retrofit.create(TVDetailApiInterface::class.java)


    @Provides
    @Singleton
    fun provideMovieDetailApiInterface(retrofit: Retrofit) : MovieDetailApiInterface =
        retrofit.create(MovieDetailApiInterface::class.java)

}