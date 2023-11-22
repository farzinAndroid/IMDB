package com.farzin.imdb.di

import com.farzin.imdb.data.remote.EpisodeGuideApiInterface
import com.farzin.imdb.data.remote.HomeApiInterface
import com.farzin.imdb.data.remote.MovieDetailApiInterface
import com.farzin.imdb.data.remote.ProfileApiInterface
import com.farzin.imdb.data.remote.SearchApiInterface
import com.farzin.imdb.data.remote.TVDetailApiInterface
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
    fun provideHomeApiInterface(retrofit: Retrofit): HomeApiInterface =
        retrofit.create(HomeApiInterface::class.java)


    @Provides
    @Singleton
    fun provideSignInApiInterface(retrofit: Retrofit): ProfileApiInterface =
        retrofit.create(ProfileApiInterface::class.java)


    @Provides
    @Singleton
    fun provideTVDetailApiInterface(retrofit: Retrofit): TVDetailApiInterface =
        retrofit.create(TVDetailApiInterface::class.java)


    @Provides
    @Singleton
    fun provideMovieDetailApiInterface(retrofit: Retrofit): MovieDetailApiInterface =
        retrofit.create(MovieDetailApiInterface::class.java)


    @Provides
    @Singleton
    fun provideSearchApiInterface(retrofit: Retrofit): SearchApiInterface =
        retrofit.create(SearchApiInterface::class.java)

    @Provides
    @Singleton
    fun provideEpisodeGuideApiInterface(retrofit: Retrofit): EpisodeGuideApiInterface =
        retrofit.create(EpisodeGuideApiInterface::class.java)

}