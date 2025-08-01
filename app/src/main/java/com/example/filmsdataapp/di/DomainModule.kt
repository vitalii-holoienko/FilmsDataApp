package com.example.filmsdataapp.di

import com.example.filmsdataapp.domain.model.Actor
import com.example.filmsdataapp.domain.repository.ActorsRepository
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import com.example.filmsdataapp.domain.usecase.GetActorBioByIdUseCase
import com.example.filmsdataapp.domain.usecase.GetActorInfoByIdUseCase
import com.example.filmsdataapp.domain.usecase.GetActorsUseCase
import com.example.filmsdataapp.domain.usecase.GetComingSoonMoviesUseCase
import com.example.filmsdataapp.domain.usecase.GetCurrentlyTrendingMoviesUseCase
import com.example.filmsdataapp.domain.usecase.GetMostPopularMoviesUseCase
import com.example.filmsdataapp.domain.usecase.GetMostPopularTVShowsUseCase
import com.example.filmsdataapp.domain.usecase.GetNewsUseCase
import com.example.filmsdataapp.domain.usecase.GetReviewsByIdUseCase
import com.example.filmsdataapp.domain.usecase.GetTitleByIdUseCase
import com.example.filmsdataapp.domain.usecase.SearchTitleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(ViewModelComponent::class)
//class DomainModule {
//    @Provides
//    fun provideGetActorBioByIdUseCase(actorsRepository : ActorsRepository) : GetActorBioByIdUseCase {
//        return GetActorBioByIdUseCase(actorsRepository)
//    }
//    @Provides
//    fun provideGetActorInfoByIdUseCase(actorsRepository: ActorsRepository) : GetActorInfoByIdUseCase {
//        return GetActorInfoByIdUseCase(actorsRepository)
//    }
//    @Provides
//    fun provideGetActorsUseCase(actorsRepository: ActorsRepository) : GetActorsUseCase {
//        return GetActorsUseCase(actorsRepository)
//    }
//    @Provides
//    fun provideGetComingSoonMoviesUseCase(moviesRepository: MoviesRepository) : GetComingSoonMoviesUseCase {
//        return GetComingSoonMoviesUseCase(moviesRepository)
//    }
//    @Provides
//    fun provideGetCurrentlyTrendingMoviesUseCase(moviesRepository: MoviesRepository) : GetCurrentlyTrendingMoviesUseCase {
//        return GetCurrentlyTrendingMoviesUseCase(moviesRepository)
//    }
//    @Provides
//    fun provideGetMostPopularMoviesUseCase(moviesRepository: MoviesRepository) : GetMostPopularMoviesUseCase {
//        return GetMostPopularMoviesUseCase(moviesRepository)
//    }
//    @Provides
//    fun provideGetMostPopularTVShowsUseCase(tVShowsRepository : TVShowsRepository) : GetMostPopularTVShowsUseCase {
//        return GetMostPopularTVShowsUseCase(tVShowsRepository)
//    }
//    @Provides
//    fun provideGetNewsUseCase(newsRepository : NewsRepository) : GetNewsUseCase {
//        return GetNewsUseCase(newsRepository)
//    }
//    @Provides
//    fun provideGetReviewsByIdUseCase(titleRepository: TitleRepository) : GetReviewsByIdUseCase {
//        return GetReviewsByIdUseCase(titleRepository)
//    }
//    @Provides
//    fun provideGetTitleByIdUseCase(moviesRepository: MoviesRepository) : GetTitleByIdUseCase {
//        return GetTitleByIdUseCase(moviesRepository)
//    }
//    @Provides
//    fun provideSearchTitleUseCase(titleRepository: TitleRepository) : SearchTitleUseCase {
//        return SearchTitleUseCase(titleRepository)
//    }
//}
