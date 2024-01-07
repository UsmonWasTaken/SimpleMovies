package app.simple.movies.common.di

import dagger.Module
import dagger.Provides
import app.simple.movies.common.di.Dispatcher.Name.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object CommonModule {

    @Provides
    @Singleton
    @Dispatcher(IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}