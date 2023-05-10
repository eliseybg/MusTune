package com.breaktime.mustune.ui_kit.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> ContentBottomSheet(
    state: ContentBottomSheetState<T>,
    sheetContent: @Composable (T) -> Unit,
    content: @Composable () -> Unit
) {
    var sheetItem by remember { mutableStateOf<T?>(null) }
    LaunchedEffect(key1 = state.value) {
        if (state.value != null) {
            sheetItem = state.value
            state.modalBottomSheetState.show()
        } else {
            state.modalBottomSheetState.hide()
            sheetItem = null
        }
    }
    ModalBottomSheetLayout(
        sheetState = state.modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetContent = { sheetItem?.let { sheetContent(it) } ?: Divider() },
        content = content
    )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun <T> rememberContentBottomSheetState(
    initialValue: ContentBottomSheetValue<T> = ContentBottomSheetValue.Hidden(),
    confirmStateChange: (ContentBottomSheetValue<T>) -> Boolean = { true }
): ContentBottomSheetState<T> {
    var closeCallback: (() -> Unit)? = null
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden) {
        closeCallback?.invoke()
        true
    }
    return remember {
        ContentBottomSheetState(sheetState, confirmStateChange).also {
            closeCallback = { it.hide() }
            if (initialValue is ContentBottomSheetValue.Shown) it.show(initialValue.value)
        }
    }
}

sealed class ContentBottomSheetValue<T> {
    data class Shown<T>(val value: T) : ContentBottomSheetValue<T>()
    class Hidden<T> : ContentBottomSheetValue<T>()
}

class ContentBottomSheetState<T> @OptIn(ExperimentalMaterialApi::class) constructor(
    internal val modalBottomSheetState: ModalBottomSheetState,
    private val confirmStateChange: (ContentBottomSheetValue<T>) -> Boolean
) {
    var value by mutableStateOf<T?>(null)

    @OptIn(ExperimentalMaterialApi::class)
    val isVisible: Boolean get() = modalBottomSheetState.isVisible

    fun show(value: T) {
        this.value = value
        confirmStateChange(ContentBottomSheetValue.Shown(value))
    }

    fun hide() {
        this.value = null
        confirmStateChange(ContentBottomSheetValue.Hidden())
    }
}