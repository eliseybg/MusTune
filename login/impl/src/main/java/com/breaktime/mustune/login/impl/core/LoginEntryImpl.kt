package com.breaktime.mustune.login.impl.core

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.injectedViewModel
import com.breaktime.mustune.common.find
import com.breaktime.mustune.login.api.LoginEntry
import com.breaktime.mustune.login.impl.di.DaggerLoginComponent
import com.breaktime.mustune.login.impl.presentation.LoginContract
import com.breaktime.mustune.login.impl.presentation.LoginViewModel
import com.breaktime.mustune.login.impl.presentation.OnBoarding
import com.breaktime.mustune.login.impl.presentation.SignIn
import com.breaktime.mustune.login.impl.presentation.SignUp
import com.breaktime.mustune.login.impl.presentation.SplashScreen
import com.breaktime.mustune.main.api.MainEntry
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.session_manager.api.LocalSessionManagerProvider
import com.breaktime.mustune.ui_kit.common.PrimaryLoadingProgress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginEntryImpl @Inject constructor() : LoginEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val sessionManager = LocalSessionManagerProvider.current
        val viewModel = injectedViewModel {
            DaggerLoginComponent.builder()
                .sessionManagerProvider(sessionManager)
                .build()
                .viewModel
        }
        val loginNavController = rememberNavController()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val state by viewModel.uiState.collectAsState()

        LaunchedEffect(key1 = true) {
            viewModelObserver(
                viewModel, context, scope, navController, loginNavController, destinations
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.login_background),
                    contentScale = ContentScale.Crop
                ),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                PrimaryLoadingProgress()
            }
            NavHost(navController = loginNavController, startDestination = rootScreen.name) {
                composable(LoginScreen.SPLASH.name) {
                    SplashScreen(viewModel)
                }
                composable(LoginScreen.ONBOARDING.name) {
                    OnBoarding(loginNavController)
                }
                composable(LoginScreen.SIGN_IN.name) {
                    SignIn(loginNavController, viewModel)
                }
                composable(LoginScreen.SIGN_UP.name) {
                    SignUp(loginNavController, viewModel)
                }
            }
        }
    }
}

private fun viewModelObserver(
    viewModel: LoginViewModel,
    context: Context,
    scope: CoroutineScope,
    navController: NavHostController,
    loginNavController: NavHostController,
    destinations: Destinations,
) {
    viewModel.effect.onEach {
        when (it) {
            LoginContract.Effect.Authorized -> {
                navController.popBackStack()
                navController.navigate(destinations.find<MainEntry>().destination())
            }

            LoginContract.Effect.UnAuthorized -> {
                loginNavController.popBackStack()
                loginNavController.navigate(LoginEntry.LoginScreen.ONBOARDING.name)
            }

            is LoginContract.Effect.ErrorMessage -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }.launchIn(scope)
}