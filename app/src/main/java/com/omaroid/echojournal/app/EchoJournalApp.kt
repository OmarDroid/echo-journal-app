package com.omaroid.echojournal.app

import android.app.Application
import com.omaroid.echojournal.BuildConfig
import com.omaroid.echojournal.app.di.appModule
import com.omaroid.echojournal.core.database.di.databaseModule
import com.omaroid.echojournal.echos.di.echoModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class EchoJournalApp: Application() {

    val coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@EchoJournalApp)
            modules (
                appModule,
                echoModule,
                databaseModule
            )
        }
    }
}