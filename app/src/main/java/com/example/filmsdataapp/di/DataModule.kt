package com.example.filmsdataapp.di

import android.content.Context
import com.example.filmsdataapp.data.repository.ActorsRepositoryImpl
import com.example.filmsdataapp.data.repository.MoviesRepositoryImpl
import com.example.filmsdataapp.data.repository.NewsRepositoryImpl
import com.example.filmsdataapp.data.repository.TVShowsRepositoryImpl
import com.example.filmsdataapp.data.repository.TitleRepositoryImpl
import com.example.filmsdataapp.data.repository.UserRepositoryImpl
import com.example.filmsdataapp.domain.repository.ActorsRepository
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import com.example.filmsdataapp.domain.repository.UserRepository
import com.example.filmsdataapp.domain.usecase.DeleteTitleFromAllListsUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideUserRepository(
        @ApplicationContext
        context: Context,
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
        storage: FirebaseStorage,
    ): UserRepository {
        return UserRepositoryImpl(context, auth, firestore, storage)
    }
}