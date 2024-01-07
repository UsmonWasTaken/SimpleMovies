package app.simple.movies.data.di

import android.content.Context
import app.simple.movies.data.storage.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object StorageModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        AppDatabase.getInstance(context)
}


