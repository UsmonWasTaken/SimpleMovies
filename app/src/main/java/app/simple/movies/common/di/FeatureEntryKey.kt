package app.simple.movies.common.di

import dagger.MapKey
import app.simple.movies.entry.FeatureEntry
import kotlin.reflect.KClass

@MapKey
annotation class FeatureEntryKey(val value: KClass<out FeatureEntry>)