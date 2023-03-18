package com.breaktime.mustune.share_file.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.breaktime.mustune.common.composable.Toolbar
import com.breaktime.mustune.resources.R

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
                    )
                },
                content = {
                    Text(
                        text = "Share settings",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                actions = {
                    Text(
                        modifier = Modifier.clickable { viewModel.setEvent(ShareFileContract.Event.OnSaveClick) },
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
            Text(text = "Provide access to file", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            StrokeTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.searchText,
                onValueChange = { viewModel.setEvent(ShareFileContract.Event.UpdateSearchText(it)) },
                hint = "Person’s username or email"
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Have access to file", fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
                        Divider(color = Color(0xFFD6D6D6), thickness = 1.dp)
                }
            }
        }
    }
}

@Composable
fun SharedUserItem(
    modifier: Modifier = Modifier,
    email: String,
    username: String?,
    onDeleteClick: () -> Unit
) {
    Row(modifier = modifier.padding(all = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = email, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(
                text = username ?: "No username",
                fontSize = 15.sp,
                color = if (username != null) Color.Black else Color.Gray
            )

        }
        Icon(
            modifier = Modifier.clickable(onClick = onDeleteClick),
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = "delete icon"
        )
    }
}

@Composable
fun StrokeTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = ""
) {
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
                    .padding(horizontal = 6.dp),
                value = value,
                onValueChange = onValueChange
            )
        }
        Divider(color = Color.Gray)
    }
}