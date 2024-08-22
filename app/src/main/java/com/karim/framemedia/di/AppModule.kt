package com.karim.framemedia.di

import com.karim.framemedia.data.ApiServices
import com.karim.framemedia.data.ArtworksRepositoryImpl
import com.karim.framemedia.data.RetrofitProvider
import com.karim.framemedia.domain.ArtworksRepository
import com.karim.framemedia.domain.usecase.GetArtworksUseCase
import com.karim.framemedia.presentation.feature_list.ArtworksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { RetrofitProvider().getInstance() }
    single { get<Retrofit>().create(ApiServices::class.java) }
    single<ArtworksRepository> { ArtworksRepositoryImpl(apiServices = get()) }
    single<GetArtworksUseCase> { GetArtworksUseCase(repository = get()) }
    viewModel { ArtworksViewModel(getArtworksUseCase = get()) }
}