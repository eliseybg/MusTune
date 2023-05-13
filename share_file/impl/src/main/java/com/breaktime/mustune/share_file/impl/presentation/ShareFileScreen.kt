package com.breaktime.mustune.share_file.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.share_file.impl.presentation.elements.SharedUserItem
import com.breaktime.mustune.ui_kit.common.Toolbar
import com.breaktime.mustune.ui_kit.elements.SearchTextField

@Composable
fun ShareFileScreen(viewModel: ShareFileViewModel, navController: NavHostController) {
    val state by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            Toolbar(
                navigation = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navController.popBackStack() },
                        imageVector = Icons.Default.Close,
                        contentDescription = "back icon",
                        tint = MusTuneTheme.colors.content
                    )
                },
                content = {
                    Text(
                        text = stringResource(id = R.string.share_settings),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MusTuneTheme.colors.content
                    )
                },
                actions = {
                    Text(
                        modifier = Modifier.clickable { viewModel.setEvent(ShareFileContract.Event.OnSaveClick) },
                        text = stringResource(id = R.string.save),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MusTuneTheme.colors.content
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
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = stringResource(R.string.provide_access_to_file), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            SearchTextField(
                modifier = Modifier.fillMaxWidth(),
                searchText = state.searchText,
                hint = stringResource(R.string.person_s_username_or_email),
                onChangeSearchText = { text ->
                    viewModel.setEvent(ShareFileContract.Event.UpdateSearchText(text))
                },
                onClearedClick = {
                    viewModel.setEvent(ShareFileContract.Event.UpdateSearchText(""))
                }
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = stringResource(R.string.have_access_to_file), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                itemsIndexed(state.sharedUsers) { index, sharedUser ->
                    SharedUserItem(
                        modifier = Modifier.fillMaxWidth(),
                        email = sharedUser.email,
                        username = sharedUser.username,
                        onDeleteClick = {
                            viewModel.setEvent(ShareFileContract.Event.OnRemoveUserClick(sharedUser))
                        }
                    )
                    if (index < state.sharedUsers.lastIndex)
                        Divider(color = MusTuneTheme.colors.divider, thickness = 1.dp)
                }
            }
        }
    }
}