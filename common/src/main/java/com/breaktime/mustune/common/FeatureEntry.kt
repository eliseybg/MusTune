package com.breaktime.mustune.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
    )
}

interface NavigationFeatureEntry : FeatureEntry {
    fun NavGraphBuilder.subNavigation(
        destinations: Destinations,
        startDestination: String,
        builder: NavGraphBuilder.(NavHostController) -> Unit = {}
    ) {
        composable(featureRoute, arguments, deepLinks) { backStackEntry ->
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { Composable(navController, destinations, backStackEntry) },
            ) {
                NavHost(
                    modifier = Modifier.padding(it),
                    navController = navController,
                    startDestination = startDestination,
                    route = featureRoute,
                    builder = { builder(navController) }
                )
            }
        }
    }

    @Composable
    fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    )
}

inline fun <reified T : FeatureEntry> Destinations.find(): T =
    findOrNull(T::class.java) ?: error("Unable to find '${T::class.java}' destination.")

fun <T : FeatureEntry> Destinations.find(type: Class<T>): T =
    findOrNull(type) ?: error("Unable to find '${type::class.java}' destination.")

fun <T : FeatureEntry> Destinations.findOrNull(type: Class<T>): T? = this[type] as? T