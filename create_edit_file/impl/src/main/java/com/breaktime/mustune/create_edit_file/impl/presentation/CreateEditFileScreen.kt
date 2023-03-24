package com.breaktime.mustune.create_edit_file.impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.extentions.pxToDp
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.ui_kit.common.PrimaryCheckbox
import com.breaktime.mustune.ui_kit.common.PrimarySwitch
import com.breaktime.mustune.ui_kit.common.PrimaryTextButton
import com.breaktime.mustune.ui_kit.common.PrimaryTextButtonDefaults
import com.breaktime.mustune.ui_kit.common.PrimaryTextField
import com.breaktime.mustune.ui_kit.common.Toolbar

@Composable
fun CreateEditFileScreen(
    viewModel: CreateEditFileViewModel,
    navController: NavHostController
) {
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
                    )
                },
                content = {
                    Text(
                        text = if (state.isEdit) "Edit file" else "Load file",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                actions = {
                    Text(
                        modifier = Modifier.clickable { viewModel.setEvent(CreateEditFileContract.Event.OnSaveClick) },
                        text = "save",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
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
            PrimaryTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.title,
                onValueChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateTitleText(it))
                },
                onClearedClick = {
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateTitleText(""))
                },
                hint = "Title"
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.artist,
                onValueChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateArtistText(it))
                },
                onClearedClick = {
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateArtistText(""))
                },
                hint = "Artist"
            )
            Spacer(modifier = Modifier.height(40.dp))
            PrimarySwitch(
                modifier = Modifier.fillMaxWidth(),
                text = "Is downloadable?",
                checked = state.isDownloadable,
                onCheckedChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.OnChangeDownloadableEnabled)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            PrimarySwitch(
                modifier = Modifier.fillMaxWidth(),
                text = "Is shareable?",
                checked = state.shareState is ShareState.Shared,
                onCheckedChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.OnChangeShareableEnabled)
                }
            )
            if (state.shareState is ShareState.Shared) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Who have access?",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                ShareTypesDropDown(
                    selected = state.shareState as ShareState.Shared,
                    items = listOf(
                        ShareState.Shared.AllUsers,
                        ShareState.Shared.OnlyInvited(),
                        ShareState.Shared.AnyOneWithLink
                    ),
                    onSelect = {
                        viewModel.setEvent(CreateEditFileContract.Event.OnSelectShareType(it))
                    }
                )
                if (state.shareState is ShareState.Shared.OnlyInvited) {
                    Spacer(modifier = Modifier.height(16.dp))
                    PrimaryCheckbox(
                        text = "Allow others to share",
                        checked = (state.shareState as ShareState.Shared.OnlyInvited).allowOtherToShare,
                        onCheckedChange = { viewModel.setEvent(CreateEditFileContract.Event.OnChangeAllowOtherToShare) }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            if (state.isEdit) PrimaryTextButton(
                text = "Remove file",
                onClick = {
                },
                colors = PrimaryTextButtonDefaults.primaryTextButtonColors(
                    enabledButtonColor = MusTuneTheme.colors.secondary,
                    enabledTextColor = MusTuneTheme.colors.delete
                )
            )
            else PrimaryTextButton(
                text = "Select file",
                onClick = {},
                colors = PrimaryTextButtonDefaults.primaryTextButtonColors(
                    enabledButtonColor = MusTuneTheme.colors.secondary,
                    enabledTextColor = MusTuneTheme.colors.content
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun ShareTypesDropDown(
    selected: ShareState.Shared,
    items: List<ShareState.Shared>,
    onSelect: (ShareState.Shared) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var width by remember { mutableStateOf(0) }

    Box(contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .onPlaced { width = it.size.width }
                .clip(RoundedCornerShape(5.dp))
                .clickable(onClick = { expanded = true })
                .background(MusTuneTheme.colors.secondary)
                .padding(horizontal = 6.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = selected.name,
                fontSize = 16.sp
            )
            Image(
                painter = painterResource(id = R.drawable.ic_drop_down),
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(width.pxToDp())
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    modifier = Modifier.height(30.dp),
                    onClick = {
                        onSelect(item)
                        expanded = false
                    },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    if (selected == item) Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(horizontal = 4.dp),
                        imageVector = Icons.Default.Check,
                        contentDescription = null
                    ) else Spacer(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(horizontal = 4.dp)
                    )
                    Text(text = item.name)
                }
            }
        }
    }
}