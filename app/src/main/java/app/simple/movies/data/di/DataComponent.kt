package app.simple.movies.data.di

import app.simple.movies.common.di.CommonProvider
import app.simple.movies.data.DataProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [CommonProvider::class],
    modules = [DataModule::class]
)
interface DataComponent : DataProvider