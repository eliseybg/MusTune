package com.breaktime.mustune.login.impl.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.find
import com.breaktime.mustune.login.api.LoginEntry
import com.breaktime.mustune.main.api.MainEntry
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.resources.theme.inter
import com.breaktime.mustune.resources.theme.overlock
import com.breaktime.mustune.resources.theme.pirataOne
import com.breaktime.mustune.ui_kit.common.PrimaryTextField
import kotlinx.coroutines.delay

@Composable
fun FullLogo(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            modifier = Modifier.height(124.dp),
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "app logo"
        )
        Text(
            text = "MusTune",
            fontSize = 44.sp,
            fontFamily = overlock
        )

        Text(
            text = "Break time",
            fontSize = 33.sp,
            color = MusTuneTheme.colors.primary,
            fontFamily = pirataOne
        )
    }
}

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.height(124.dp),
        painter = painterResource(id = R.drawable.app_logo),
        contentDescription = "app logo"
    )
}

@Composable
fun SplashScreen(loginNavController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        loginNavController.popBackStack()
        loginNavController.navigate(LoginEntry.LoginScreen.ONBOARDING.name)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FullLogo()
    }
}

@Composable
fun OnBoarding(loginNavController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        FullLogo(modifier = Modifier.scale(0.8f))
        Spacer(modifier = Modifier.weight(0.15f))

        Column(
            modifier = Modifier
                .weight(0.6f)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello",
                fontSize = 24.sp,
                fontFamily = overlock
            )
            Text(
                modifier = Modifier.padding(horizontal = 26.dp),
                text = "Start learning how to play musical instruments and join us",
                fontSize = 20.sp,
                fontFamily = overlock
            )
            Spacer(modifier = Modifier.weight(0.2f))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    loginNavController.popBackStack()
                    loginNavController.navigate(LoginEntry.LoginScreen.SIGN_IN.name)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF5A6993)
                ),
                shape = RoundedCornerShape(10.dp),
                interactionSource = MutableInteractionSource()
            ) {
                Text(
                    text = "Log in",
                    fontSize = 22.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = "Don’t you have account? ",
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier.clickable {
                        loginNavController.popBackStack()
                        loginNavController.navigate(LoginEntry.LoginScreen.SIGN_UP.name)
                    },
                    text = "Register",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.35f))
    }
}

@Composable
fun SignIn(
    navController: NavController,
    loginNavController: NavHostController,
    destinations: Destinations
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        Logo(modifier = Modifier.scale(0.9f))
        Spacer(modifier = Modifier.weight(0.1f))

        Column(
            modifier = Modifier
                .weight(0.7f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Log in",
                fontSize = 37.sp,
                fontFamily = overlock
            )
            Spacer(modifier = Modifier.height(40.dp))

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            PrimaryTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = email,
                onValueChange = { email = it },
                hint = "email"
            )

            Spacer(modifier = Modifier.height(35.dp))

            PrimaryTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = password,
                onValueChange = { password = it },
                hint = "password"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.align(Alignment.End),
                text = "Forget password",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006CD0)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(destinations.find<MainEntry>().featureRoute)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF5A6993)
                ),
                shape = RoundedCornerShape(10.dp),
                interactionSource = MutableInteractionSource()
            ) {
                Text(
                    text = "Log in",
                    fontSize = 22.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Don’t you have account? ",
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier.clickable {
                        loginNavController.popBackStack()
                        loginNavController.navigate(LoginEntry.LoginScreen.SIGN_UP.name)
                    },
                    text = "Register",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            OrDivider()
            Spacer(modifier = Modifier.height(20.dp))
            SocialMedia(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun OrDivider(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(modifier = Modifier.weight(1f), color = MusTuneTheme.colors.content)
        Text(modifier = Modifier.padding(horizontal = 10.dp), text = "or")
        Divider(modifier = Modifier.weight(1f), color = MusTuneTheme.colors.content)
    }
}

@Composable
fun SocialMedia(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(32.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.ic_facebook_logo),
            contentDescription = "facebook"
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_google_logo),
            contentDescription = "google"
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_apple_logo),
            contentDescription = "apple"
        )
    }
}

@Composable
fun SignUp(
    navController: NavController,
    loginNavController: NavHostController,
    destinations: Destinations
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        Logo(modifier = Modifier.scale(0.9f))
        Spacer(modifier = Modifier.weight(0.1f))

        Column(
            modifier = Modifier
                .weight(0.7f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Sign up",
                fontSize = 37.sp,
                fontFamily = overlock
            )
            Spacer(modifier = Modifier.height(40.dp))

            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            PrimaryTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = username,
                onValueChange = { username = it },
                hint = "username"
            )

            Spacer(modifier = Modifier.height(35.dp))

            PrimaryTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = email,
                onValueChange = { email = it },
                hint = "email"
            )

            Spacer(modifier = Modifier.height(35.dp))

            PrimaryTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = password,
                onValueChange = { password = it },
                hint = "password"
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(destinations.find<MainEntry>().featureRoute)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF5A6993)
                ),
                shape = RoundedCornerShape(10.dp),
                interactionSource = MutableInteractionSource()
            ) {
                Text(
                    text = "Log in",
                    fontSize = 22.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Do you have account? ",
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier.clickable {
                        loginNavController.popBackStack()
                        loginNavController.navigate(LoginEntry.LoginScreen.SIGN_IN.name)
                    },
                    text = "Log in",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            OrDivider()
            Spacer(modifier = Modifier.height(20.dp))
            SocialMedia(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}