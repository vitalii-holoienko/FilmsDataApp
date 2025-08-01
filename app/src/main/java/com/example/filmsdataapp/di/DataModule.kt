package com.example.filmsdataapp.di

import com.example.filmsdataapp.data.repository.ActorsRepositoryImpl
import com.example.filmsdataapp.data.repository.MoviesRepositoryImpl
import com.example.filmsdataapp.data.repository.NewsRepositoryImpl
import com.example.filmsdataapp.data.repository.TVShowsRepositoryImpl
import com.example.filmsdataapp.data.repository.TitleRepositoryImpl
import com.example.filmsdataapp.domain.repository.ActorsRepository
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideActorsRepository() : ActorsRepository{
        return ActorsRepositoryImpl()
    }
    @Provides
    @Singleton
    fun provideMoviesRepository() : MoviesRepository {
        return MoviesRepositoryImpl()
    }
    @Provides
    @Singleton
    fun provideNewsRepository() : NewsRepository {
        return NewsRepositoryImpl()
    }
    @Provides
    @Singleton
    fun provideTitleRepository() : TitleRepository {
        return TitleRepositoryImpl()
    }
    @Provides
    @Singleton
    fun provideTVShowsRepository() : TVShowsRepository {
        return TVShowsRepositoryImpl()
    }
}