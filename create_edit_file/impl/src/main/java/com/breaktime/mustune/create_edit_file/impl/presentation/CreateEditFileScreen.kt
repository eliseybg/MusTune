package com.breaktime.mustune.create_edit_file.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.resources.theme.inter
import com.breaktime.mustune.ui_kit.common.PrimarySwitch
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
                hint = "Title"
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.artist,
                onValueChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateArtistText(it))
                },
                hint = "Artist"
            )
            Spacer(modifier = Modifier.height(40.dp))
//is downloadable
            PrimarySwitch(
                modifier = Modifier.fillMaxWidth(),
                text = "Is downloadable?",
                checked = state.isDownloadable,
                onCheckedChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.OnChangeDownloadableEnabled)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
//is shareable
            PrimarySwitch(
                modifier = Modifier.fillMaxWidth(),
                text = "Is shareable?",
                checked = state.shareState is ShareState.Shared,
                onCheckedChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.OnChangeShareableEnabled)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
//who has access
            Text(
                text = "Who have access?",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
//drop down
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(MusTuneTheme.colors.secondary)
            )
            Spacer(modifier = Modifier.height(16.dp))
//allow other to share
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = false, onCheckedChange = {})
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Who have access?",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (state.isEdit) {
//remove file
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MusTuneTheme.colors.secondary
                    ),
                    shape = RoundedCornerShape(10.dp),
                    interactionSource = MutableInteractionSource()
                ) {
                    Text(
                        text = "Remove file",
                        fontSize = 22.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight.Bold,
                        color = MusTuneTheme.colors.delete
                    )
                }
            } else {
//add file
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MusTuneTheme.colors.secondary
                    ),
                    shape = RoundedCornerShape(10.dp),
                    interactionSource = MutableInteractionSource()
                ) {
                    Text(
                        text = "Load file",
                        fontSize = 22.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight.Bold,
                        color = MusTuneTheme.colors.content
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}