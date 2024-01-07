package app.simple.movies.common.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.VALUE_PARAMETER
)
annotation class Dispatcher(val name: Name) {
    enum class Name {
        Main,
        Default,
        IO
    }
}
