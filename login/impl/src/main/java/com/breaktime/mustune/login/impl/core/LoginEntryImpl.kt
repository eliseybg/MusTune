package com.breaktime.mustune.login.impl.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.injectedViewModel
import com.breaktime.mustune.login.api.LoginEntry
import com.breaktime.mustune.login.impl.di.DaggerLoginComponent
import com.breaktime.mustune.login.impl.presentation.OnBoarding
import com.breaktime.mustune.login.impl.presentation.SignIn
import com.breaktime.mustune.login.impl.presentation.SignUp
import com.breaktime.mustune.login.impl.presentation.SplashScreen
import com.breaktime.mustune.resources.R
import javax.inject.Inject

class LoginEntryImpl @Inject constructor() : LoginEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val viewModel = injectedViewModel() {
            DaggerLoginComponent.builder()
                .build()
                .viewModel
        }
        val loginNavController = rememberNavController()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.login_background),
                    contentScale = ContentScale.Crop
                ),
            contentAlignment = Alignment.Center
        ) {
            NavHost(navController = loginNavController, startDestination = rootScreen.name) {
                composable(LoginScreen.SPLASH.name) {
                    SplashScreen(loginNavController)
                }
                composable(LoginScreen.ONBOARDING.name) {
                    OnBoarding(loginNavController)
                }
                composable(LoginScreen.SIGN_IN.name) {
                    SignIn(navController, loginNavController, destinations)
                }
                composable(LoginScreen.SIGN_UP.name) {
                    SignUp(navController, loginNavController, destinations)
                }
            }
        }
    }
}