package com.sar.steamaccountswitcher.common

import com.sar.steamaccountswitcher.common.logging.data.LoggerImpl
import com.sar.steamaccountswitcher.common.logging.domain.Logger
import org.koin.dsl.module

private val dataModule = module {
    single<Logger> { LoggerImpl() }
}

private val domainModule = module {

}

private val presentationModule = module {

}

val commonModule = dataModule + domainModule + presentationModule
