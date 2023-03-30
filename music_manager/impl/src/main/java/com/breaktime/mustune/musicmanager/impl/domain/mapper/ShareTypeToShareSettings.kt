package com.breaktime.mustune.musicmanager.impl.domain.mapper

import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.impl.data.entities.ShareType

fun ShareType.toShareSettings() = when (this) {
    ShareType.UNSHARED -> ShareSettings.NoSharing
    ShareType.ONLY_INVITED -> ShareSettings.Shared.OnlyInvited(false)
    ShareType.ONLY_INVITED_WITH_SHARE -> ShareSettings.Shared.OnlyInvited()
    ShareType.ANYONE_WITH_LINK -> ShareSettings.Shared.AnyOneWithLink
    ShareType.ALL_USERS -> ShareSettings.Shared.AllUsers
}

fun ShareSettings.toShareType() = when (this) {
    is ShareSettings.NoSharing -> ShareType.UNSHARED
    is ShareSettings.Shared.OnlyInvited -> if (allowOtherToShare) ShareType.ONLY_INVITED_WITH_SHARE else ShareType.ONLY_INVITED
    is ShareSettings.Shared.AnyOneWithLink -> ShareType.ANYONE_WITH_LINK
    is ShareSettings.Shared.AllUsers -> ShareType.ALL_USERS
}