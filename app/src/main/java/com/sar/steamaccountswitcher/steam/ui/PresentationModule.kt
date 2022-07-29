package com.sar.steamaccountswitcher.steam.ui

import com.sar.steamaccountswitcher.steam.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel(get()) }
}
