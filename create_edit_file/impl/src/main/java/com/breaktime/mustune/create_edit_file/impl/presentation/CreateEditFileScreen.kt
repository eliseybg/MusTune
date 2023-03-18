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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.composable.CustomSwitch
import com.breaktime.mustune.common.composable.Toolbar
import com.breaktime.mustune.resources.theme.inter

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
                .background(Color(0xFFFDFDFD))
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            StrokeTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.title,
                onValueChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateTitleText(it))
                },
                hint = "Title"
            )
            Spacer(modifier = Modifier.height(16.dp))
            StrokeTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.artist,
                onValueChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateArtistText(it))
                },
                hint = "Artist"
            )
            Spacer(modifier = Modifier.height(40.dp))
//is downloadable
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Is downloadable?",
                    fontSize = 16.sp
                )
                CustomSwitch(
                    checked = state.isDownloadable,
                    onCheckedChange = {
                        viewModel.setEvent(CreateEditFileContract.Event.OnChangeDownloadableEnabled)
                    }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
//is shareable
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Is shareable?",
                    fontSize = 16.sp
                )
                CustomSwitch(
                    checked = state.shareState is ShareState.Shared,
                    onCheckedChange = {
                        viewModel.setEvent(CreateEditFileContract.Event.OnChangeShareableEnabled)
                    }
                )
            }
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
                    .background(Color.Gray)
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
                        backgroundColor = Color(0xFFF0F0F0)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    interactionSource = MutableInteractionSource()
                ) {
                    Text(
                        text = "Remove file",
                        fontSize = 22.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
            } else {
//add file
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFF0F0F0)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    interactionSource = MutableInteractionSource()
                ) {
                    Text(
                        text = "Add file",
                        fontSize = 22.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StrokeTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = ""
) {
    var hasFocus by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Box {
            if (value.isEmpty()) Text(
                modifier = Modifier.padding(horizontal = 6.dp),
                text = hint,
                fontSize = 14.sp
            )
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
                    .onFocusChanged {
                        hasFocus = it.hasFocus
                    },
                value = value,
                onValueChange = onValueChange
            )
        }
        Divider(color = if (hasFocus) Color.Blue else Color.Gray)
    }
}