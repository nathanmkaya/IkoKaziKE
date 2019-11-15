package dev.dita.ikokazike.di

import dev.dita.ikokazike.ui.favorite.FavoriteViewModel
import dev.dita.ikokazike.ui.home.HomeViewModel
import dev.dita.ikokazike.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { FavoriteViewModel() }
    viewModel { HomeViewModel() }
    viewModel { SearchViewModel() }
}