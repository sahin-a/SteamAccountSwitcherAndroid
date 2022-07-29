package com.sar.steamaccountswitcher

import android.app.Application
import com.sar.steamaccountswitcher.steam.steamModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(steamModule)
        }
    }
}
