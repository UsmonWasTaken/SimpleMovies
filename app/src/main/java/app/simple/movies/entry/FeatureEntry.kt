package app.simple.movies.entry

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink


typealias Destinations = Map<Class<out FeatureEntry>, @JvmSuppressWildcards FeatureEntry>

interface FeatureEntry {

    val featureRoute: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    val deepLinks: List<NavDeepLink>
        get() = emptyList()
}

inline fun <reified T : FeatureEntry> Destinations.find(): T =
    findOrNull() ?: error("Unable to find '${T::class.java}' destination.")

inline fun <reified T : FeatureEntry> Destinations.findOrNull(): T? =
    this[T::class.java] as? T