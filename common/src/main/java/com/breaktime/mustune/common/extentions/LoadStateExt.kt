package com.breaktime.mustune.common.extentions

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

val CombinedLoadStates.isLoading: Boolean
    get() = refresh is LoadState.Loading || append is LoadState.Loading
            || source.refresh is LoadState.Loading || source.append is LoadState.Loading