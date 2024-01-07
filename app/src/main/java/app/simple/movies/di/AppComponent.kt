package app.simple.movies.di

import dagger.Component
import app.simple.movies.common.di.CommonProvider
import app.simple.movies.data.DataProvider
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        CommonProvider::class,
        DataProvider::class,
    ],
    modules = [NavigationModule::class]
)
interface AppComponent : AppProvider