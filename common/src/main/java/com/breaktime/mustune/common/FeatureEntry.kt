package com.breaktime.mustune.common

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

typealias Destinations = Map<Class<out FeatureEntry>, @JvmSuppressWildcards FeatureEntry>

interface FeatureEntry {
    val featureRoute: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    val deepLinks: List<NavDeepLink>
        get() = emptyList()
}

interface ComposableFeatureEntry : FeatureEntry {
    fun NavGraphBuilder.composable(navController: NavHostController, destinations: Destinations) {
        composable(featureRoute, arguments, deepLinks) { backStackEntry ->
            Composable(navController, destinations, backStackEntry)
        }
    }

    @Composable
    fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    )
}

interface AggregateFeatureEntry : FeatureEntry {
    fun NavGraphBuilder.navigation(
        navController: NavHostController,
        destinations: Destinations,
        builder: NavGraphBuilder.() -> Unit = {}
    ) {
        composable(featureRoute, arguments, deepLinks) { backStackEntry ->
            Composable(navController, destinations, backStackEntry)
        }
    }

    @Composable
    fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    )
}

interface NavigationFeatureEntry : FeatureEntry {
    fun NavGraphBuilder.subNavigation(
        destinations: Destinations,
        startDestination: String,
        builder: NavGraphBuilder.(NavHostController) -> Unit = {}
    ) {
        composable(featureRoute, arguments, deepLinks) { backStackEntry ->
            Composable(destinations, backStackEntry, startDestination, builder)
        }
    }

    @Composable
    fun Composable(
        destinations: Destinations,
        backStackEntry: NavBackStackEntry,
        startDestination: String,
        builder: NavGraphBuilder.(NavHostController) -> Unit
    )
}

inline fun <reified T : FeatureEntry> Destinations.find(): T =
    findOrNull(T::class.java) ?: error("Unable to find '${T::class.java}' destination.")

fun <T : FeatureEntry> Destinations.find(type: Class<T>): T =
    findOrNull(type) ?: error("Unable to find '${type::class.java}' destination.")

fun <T : FeatureEntry> Destinations.findOrNull(type: Class<T>): T? = this[type] as? T