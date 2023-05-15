package com.breaktime.mustune.create_edit_file.impl.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.extentions.pxToDp
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.ui_kit.common.PrimaryCheckbox
import com.breaktime.mustune.ui_kit.common.PrimarySwitch
import com.breaktime.mustune.ui_kit.common.PrimaryTextButton
import com.breaktime.mustune.ui_kit.common.PrimaryTextButtonDefaults
import com.breaktime.mustune.ui_kit.common.PrimaryTextField
import com.breaktime.mustune.ui_kit.common.Toolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun CreateEditFileScreen(
    viewModel: CreateEditFileViewModel,
    navController: NavHostController
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModelObserver(viewModel, context, scope, navController)
    }

    val openFileContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri -> viewModel.setEvent(CreateEditFileContract.Event.SelectFile(uri)) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) openFileContract.launch(arrayOf("*/*"))
        else Toast.makeText(
            context,
            context.getText(R.string.you_need_to_grant_permissions),
            Toast.LENGTH_SHORT
        ).show()
    }

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
                        text = stringResource(if (state.isEdit) R.string.edit_file else R.string.load_file),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MusTuneTheme.colors.content
                    )
                },
                actions = {
                    Text(
                        modifier = Modifier.clickable(enabled = state.isSaveEnabled) {
                            viewModel.setEvent(CreateEditFileContract.Event.OnSaveClick)
                        },
                        text = stringResource(R.string.save),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = if (state.isSaveEnabled) MusTuneTheme.colors.content else MusTuneTheme.colors.deactivatedContent
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
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateTitleText(it.trim()))
                },
                onClearedClick = {
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateTitleText(""))
                },
                hint = stringResource(R.string.title)
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.artist,
                onValueChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateArtistText(it.trim()))
                },
                onClearedClick = {
                    viewModel.setEvent(CreateEditFileContract.Event.UpdateArtistText(""))
                },
                hint = stringResource(R.string.artist)
            )
            Spacer(modifier = Modifier.height(40.dp))
            PrimarySwitch(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.is_downloadable),
                checked = state.isDownloadable,
                onCheckedChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.OnChangeDownloadableEnabled)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            PrimarySwitch(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.is_shareable),
                checked = state.shareSettings is ShareSettings.Shared,
                onCheckedChange = {
                    viewModel.setEvent(CreateEditFileContract.Event.OnChangeShareableEnabled)
                }
            )
            if (state.shareSettings is ShareSettings.Shared) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.who_have_access),
                    fontSize = 14.sp,
                    color = MusTuneTheme.colors.content
                )
                Spacer(modifier = Modifier.height(16.dp))
                ShareTypesDropDown(
                    selected = state.shareSettings as ShareSettings.Shared,
                    items = listOf(
                        ShareSettings.Shared.AllUsers,
                        ShareSettings.Shared.OnlyInvited(),
                        ShareSettings.Shared.AnyOneWithLink
                    ),
                    onSelect = {
                        viewModel.setEvent(CreateEditFileContract.Event.OnSelectShareType(it))
                    }
                )
                if (state.shareSettings is ShareSettings.Shared.OnlyInvited) {
                    Spacer(modifier = Modifier.height(16.dp))
                    PrimaryCheckbox(
                        text = stringResource(R.string.allow_others_to_share),
                        checked = (state.shareSettings as ShareSettings.Shared.OnlyInvited).allowOtherToShare,
                        onCheckedChange = { viewModel.setEvent(CreateEditFileContract.Event.OnChangeAllowOtherToShare) }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            if (state.isEdit) PrimaryTextButton(
                text = stringResource(R.string.remove_file),
                onClick = { viewModel.setEvent(CreateEditFileContract.Event.OnDeleteFileClicked) },
                colors = PrimaryTextButtonDefaults.primaryTextButtonColors(
                    enabledButtonColor = MusTuneTheme.colors.secondary,
                    enabledTextColor = MusTuneTheme.colors.delete
                )
            )
            else state.attachedFileName?.let { filename ->
                FileItem(
                    filename = filename,
                    onCancelClick = {
                        viewModel.setEvent(CreateEditFileContract.Event.OnDeleteFileClicked)
                    }
                )
            } ?: PrimaryTextButton(
                text = stringResource(R.string.select_file),
                onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
                        || context.isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)
                    ) {
                        openFileContract.launch(arrayOf("*/*"))
                    } else {
                        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                },
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
    selected: ShareSettings.Shared,
    items: List<ShareSettings.Shared>,
    onSelect: (ShareSettings.Shared) -> Unit
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
                fontSize = 16.sp,
                color = MusTuneTheme.colors.content
            )
            Image(
                painter = painterResource(id = R.drawable.ic_drop_down),
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(width.pxToDp())
                .background(MusTuneTheme.colors.dropDownBackground)
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
                    Text(
                        text = item.name,
                        color = MusTuneTheme.colors.content
                    )
                }
            }
        }
    }
}

@Composable
fun FileItem(
    filename: String,
    onCancelClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MusTuneTheme.colors.primary)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_file),
            contentDescription = null,
            tint = MusTuneTheme.colors.onPrimary
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            text = filename,
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MusTuneTheme.colors.onPrimary
        )

        Button(
            modifier = Modifier.size(24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MusTuneTheme.colors.onPrimary
            ),
            onClick = onCancelClick,
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_cancel_w2),
                contentDescription = null,
                tint = MusTuneTheme.colors.primary
            )
        }
    }
}

fun Context.isPermissionGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

private fun viewModelObserver(
    viewModel: CreateEditFileViewModel,
    context: Context,
    scope: CoroutineScope,
    navController: NavHostController
) {
    viewModel.effect.onEach {
        when (it) {
            CreateEditFileContract.Effect.CloseScreen -> navController.popBackStack()
            CreateEditFileContract.Effect.WrongFileFormat -> Toast.makeText(
                context, context.getText(R.string.wrong_file_format), Toast.LENGTH_SHORT
            ).show()

            is CreateEditFileContract.Effect.ErrorMessage -> Toast.makeText(
                context, it.message.asString(context), Toast.LENGTH_SHORT
            ).show()
        }
    }.launchIn(scope)
}