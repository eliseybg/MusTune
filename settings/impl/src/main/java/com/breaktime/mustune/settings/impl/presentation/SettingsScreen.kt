package com.breaktime.mustune.settings.impl.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.find
import com.breaktime.mustune.login.api.LoginEntry
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.ui_kit.common.PrimarySwitch
import com.breaktime.mustune.ui_kit.common.PrimaryTextButton
import com.breaktime.mustune.ui_kit.common.PrimaryTextButtonDefaults
import com.breaktime.mustune.ui_kit.common.Toolbar

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    navController: NavHostController,
    destinations: Destinations
) {
    val state by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            Toolbar(
                content = {
                    Text(
                        text = stringResource(R.string.settings),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                viewModel.setEvent(SettingsContract.Event.OnLogOutClicked)
                                val route = destinations
                                    .find<LoginEntry>()
                                    .destination(LoginEntry.LoginScreen.ONBOARDING)
                                navController.popBackStack()
                                navController.navigate(route)
                            },
                        painter = painterResource(id = R.drawable.ic_log_out),
                        contentDescription = "Logout icon",
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MusTuneTheme.colors.background)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.common),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            SettingsSwitchItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                iconId = R.drawable.ic_bell_20,
                text = stringResource(R.string.notifications),
                checked = state.isNotificationsEnabled,
                onCheckedChange = {
                    viewModel.setEvent(SettingsContract.Event.OnChangeNotificationClicked)
                }
            )
            SettingsSwitchItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                iconId = R.drawable.ic_moon_20,
                text = stringResource(R.string.dark_mode),
                checked = state.isDarkModeEnabled,
                onCheckedChange = {
                    viewModel.setEvent(SettingsContract.Event.OnChangeDarkModeClicked)
                }
            )
            Text(
                modifier = Modifier.padding(top = 40.dp, start = 16.dp, end = 16.dp),
                text = stringResource(R.string.account),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            SettingsLanguageItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                iconId = R.drawable.ic_globe_20,
                text = stringResource(R.string.language)
            )
            SettingsMoreItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                iconId = R.drawable.ic_lock_20,
                text = stringResource(R.string.change_password)
            )

            if (false) {
                Text(
                    modifier = Modifier.padding(top = 40.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(R.string.social),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                SettingsSwitchItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    iconId = R.drawable.ic_bell_20,
                    text = stringResource(R.string.icloud),
                    checked = true,
                    onCheckedChange = {}
                )
                SettingsSwitchItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    iconId = R.drawable.ic_facebook_logo_20,
                    text = stringResource(R.string.facebook),
                    checked = false,
                    onCheckedChange = {}
                )
                SettingsSwitchItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    iconId = R.drawable.ic_google_logo_20,
                    text = stringResource(R.string.google),
                    checked = false,
                    onCheckedChange = {}
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            PrimaryTextButton(
                text = stringResource(R.string.remove_account),
                onClick = {
                    viewModel.setEvent(SettingsContract.Event.OnDeleteAccountClicked)
                    val route = destinations.find<LoginEntry>().featureRoute
                    navController.popBackStack()
                    navController.navigate(route)
                },
                colors = PrimaryTextButtonDefaults.primaryTextButtonColors(
                    enabledButtonColor = MusTuneTheme.colors.secondary,
                    enabledTextColor = MusTuneTheme.colors.delete
                )
            )
        }
    }
}

@Composable
fun SettingsSwitchItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    text: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
) {
    SettingsItem(
        modifier = modifier,
        iconId = iconId,
        text = "",
        option = { PrimarySwitch(Modifier, text, checked, onCheckedChange) }
    )
}

@Composable
fun SettingsLanguageItem(modifier: Modifier = Modifier, @DrawableRes iconId: Int, text: String) {
    SettingsItem(
        modifier = modifier,
        iconId = iconId,
        text = text,
        option = {
            Text(text = "English", fontSize = 14.sp, color = MusTuneTheme.colors.textHint)
            Icon(
                modifier = Modifier.padding(start = 6.dp),
                painter = painterResource(id = R.drawable.ic_arrow_right_20),
                contentDescription = null
            )
        })
}

@Composable
fun SettingsMoreItem(modifier: Modifier = Modifier, @DrawableRes iconId: Int, text: String) {
    SettingsItem(
        modifier = modifier,
        iconId = iconId,
        text = text,
        option = {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right_20),
                contentDescription = null
            )
        })
}

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    text: String,
    option: @Composable () -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = iconId), contentDescription = null)
        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f),
            text = text,
            fontSize = 16.sp
        )
        option()
    }
}