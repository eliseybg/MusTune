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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.extentions.filterUsername
import com.breaktime.mustune.login.api.LoginEntry
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.resources.theme.inter
import com.breaktime.mustune.resources.theme.overlock
import com.breaktime.mustune.resources.theme.pirataOne
import com.breaktime.mustune.ui_kit.common.PrimaryTextField

@Composable
fun FullLogo(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Logo()
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 44.sp,
            fontFamily = overlock
        )
        Text(
            text = stringResource(R.string.break_time),
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
fun SplashScreen(viewModel: LoginViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.setEvent(LoginContract.Event.CheckIsAuthorized)
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
                text = stringResource(R.string.hello),
                fontSize = 24.sp,
                fontFamily = overlock
            )
            Text(
                modifier = Modifier.padding(horizontal = 26.dp),
                text = stringResource(R.string.start_learning_how_to_play_musical_instruments_and_join_us),
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
                    text = stringResource(id = R.string.log_in),
                    fontSize = 22.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.don_t_you_have_account),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier.clickable {
                        loginNavController.popBackStack()
                        loginNavController.navigate(LoginEntry.LoginScreen.SIGN_UP.name)
                    },
                    text = stringResource(id = R.string.register),
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
    loginNavController: NavHostController,
    viewModel: LoginViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Logo(modifier = Modifier.scale(0.9f))
        Spacer(modifier = Modifier.height(80.dp))

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = R.string.log_in),
                fontSize = 37.sp,
                fontFamily = overlock
            )
            Spacer(modifier = Modifier.height(40.dp))

            PrimaryTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = state.email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { viewModel.setEvent(LoginContract.Event.UpdateEmailText(it)) },
                onClearedClick = { viewModel.setEvent(LoginContract.Event.UpdateEmailText("")) },
                hint = stringResource(R.string.username_or_email)
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = state.password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { viewModel.setEvent(LoginContract.Event.UpdatePasswordText(it)) },
                onClearedClick = { viewModel.setEvent(LoginContract.Event.UpdatePasswordText("")) },
                hint = stringResource(id = R.string.password)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.align(Alignment.End).alpha(0f),
                text = stringResource(R.string.forget_password),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006CD0)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.setEvent(LoginContract.Event.OnSignInClick)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF5A6993)
                ),
                shape = RoundedCornerShape(10.dp),
                interactionSource = MutableInteractionSource()
            ) {
                Text(
                    text = stringResource(id = R.string.log_in),
                    fontSize = 22.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = stringResource(R.string.don_t_you_have_account),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier.clickable {
                        loginNavController.popBackStack()
                        loginNavController.navigate(LoginEntry.LoginScreen.SIGN_UP.name)
                    },
                    text = stringResource(R.string.register),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OrDivider()
            Spacer(modifier = Modifier.height(16.dp))
            SocialMedia(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SignUp(
    loginNavController: NavHostController,
    viewModel: LoginViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .imePadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Logo(modifier = Modifier.scale(0.9f))
        Spacer(modifier = Modifier.height(80.dp))

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = R.string.sign_up),
                fontSize = 37.sp,
                fontFamily = overlock
            )
            Spacer(modifier = Modifier.height(40.dp))

            PrimaryTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = state.username,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = { viewModel.setEvent(LoginContract.Event.UpdateUsernameText(it.filterUsername())) },
                onClearedClick = { viewModel.setEvent(LoginContract.Event.UpdateUsernameText("")) },
                hint = stringResource(R.string.username)
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = state.email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { viewModel.setEvent(LoginContract.Event.UpdateEmailText(it)) },
                onClearedClick = { viewModel.setEvent(LoginContract.Event.UpdateEmailText("")) },
                hint = stringResource(id = R.string.email)
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = state.password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { viewModel.setEvent(LoginContract.Event.UpdatePasswordText(it)) },
                onClearedClick = { viewModel.setEvent(LoginContract.Event.UpdatePasswordText("")) },
                hint = stringResource(R.string.password)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.setEvent(LoginContract.Event.OnSignUpClick)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF5A6993)
                ),
                shape = RoundedCornerShape(10.dp),
                interactionSource = MutableInteractionSource()
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    fontSize = 22.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = stringResource(R.string.do_you_have_account),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier.clickable {
                        loginNavController.popBackStack()
                        loginNavController.navigate(LoginEntry.LoginScreen.SIGN_IN.name)
                    },
                    text = stringResource(R.string.log_in),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OrDivider()
            Spacer(modifier = Modifier.height(16.dp))
            SocialMedia(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun OrDivider(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(modifier = Modifier.weight(1f), color = MusTuneTheme.colors.content)
        Text(modifier = Modifier.padding(horizontal = 10.dp), text = stringResource(R.string.or))
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