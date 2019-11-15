package dev.dita.ikokazike

import android.app.Application
import dev.dita.ikokazike.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }
}