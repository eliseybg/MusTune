package com.breaktime.mustune.main.impl.core

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.FeatureEntry
import com.breaktime.mustune.common.find
import com.breaktime.mustune.main.api.MainEntry
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.music.api.MusicEntry
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.settings.api.SettingsEntry
import com.breaktime.mustune.ui_kit.common.shadow
import javax.inject.Inject

class MainEntryImpl @Inject constructor() : MainEntry() {
    @Composable
    override fun Composable(
        destinations: Destinations,
        backStackEntry: NavBackStackEntry,
        startDestination: String,
        builder: NavGraphBuilder.(NavHostController) -> Unit
    ) {
        val innerNavController = rememberNavController()
        Scaffold(
            bottomBar = {
                val bottomNavigationItems = listOf(
                    BottomNavigationScreens.Music,
                    BottomNavigationScreens.Settings
                )
                MainBottomNavigation(innerNavController, bottomNavigationItems, destinations)
            },
        ) {
            NavHost(
                modifier = Modifier.padding(it),
                navController = innerNavController,
                startDestination = startDestination,
                route = featureRoute,
                builder = { builder(innerNavController) }
            )
        }
    }
}

@Composable
private fun NavHostController.isCurrentRoute(route: String): Boolean {
    val navBackStackEntry by currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.hierarchy?.any { it.route == route } == true
}

@Composable
private fun MainBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>,
    destinations: Destinations
) {
    BottomNavigation(
        modifier = Modifier
            .height(50.dp)
            .shadow(
                color = Color.Black.copy(0.25f),
                blurRadius = 4.dp,
                spread = 1.5.dp,
                offsetY = 0.dp
            ),
        backgroundColor = MusTuneTheme.colors.bottomBar,
    ) {
        items.forEach { screen ->
            val route = destinations.find(screen.featureEntry).featureRoute
            val isSelected = navController.isCurrentRoute(route)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        navController.popBackStack()
                        navController.navigate(route) {
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.animateContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val activeColor = MusTuneTheme.colors.primary
                    val inactiveColor = MusTuneTheme.colors.unselectedBottomBarItem
                    val bottomNavigationAnimationSpec = TweenSpec<Float>(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )

                    val animationProgress by animateFloatAsState(
                        targetValue = if (isSelected) 1f else 0f,
                        animationSpec = bottomNavigationAnimationSpec
                    )

                    val color = lerp(inactiveColor, activeColor, animationProgress)

                    CompositionLocalProvider(
                        LocalContentColor provides color.copy(alpha = 1f),
                        LocalContentAlpha provides color.alpha,
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(if (isSelected) 30.dp else 24.dp)
                                .animateContentSize(),
                            painter = painterResource(id = screen.iconId),
                            contentDescription = null
                        )
                        if (!isSelected) Text(text = screen.titleId, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

sealed class BottomNavigationScreens(
    val iconId: Int,
    val titleId: String,
    val featureEntry: Class<out FeatureEntry>,
) {
    object Music : BottomNavigationScreens(R.drawable.ic_music_30, "Music", MusicEntry::class.java)
    object Settings :
        BottomNavigationScreens(R.drawable.ic_settings_30, "Settings", SettingsEntry::class.java)
}
