package app.simple.movies

import android.app.Application
import app.simple.movies.common.di.DaggerCommonComponent
import app.simple.movies.data.di.DaggerDataComponent
import app.simple.movies.di.AppProvider
import app.simple.movies.di.DaggerAppComponent


class MoviesApplication : Application() {

    lateinit var appProvider: AppProvider
        private set

    override fun onCreate() {
        super.onCreate()
        val commonProvider = DaggerCommonComponent.factory()
            .create(applicationContext = this)
        val dataProvider = DaggerDataComponent.builder()
            .commonProvider(commonProvider)
            .build()
        appProvider = DaggerAppComponent.builder()
            .commonProvider(commonProvider)
            .dataProvider(dataProvider)
            .build()
    }
}

val Application.appProvider: AppProvider
    get() = (this as MoviesApplication).appProvider