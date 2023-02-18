package com.breaktime.mustune.common.di

import com.breaktime.mustune.common.FeatureEntry
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class FeatureEntryKey(val value: KClass<out FeatureEntry>)