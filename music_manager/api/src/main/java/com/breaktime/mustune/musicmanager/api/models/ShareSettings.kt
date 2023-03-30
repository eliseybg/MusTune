package com.breaktime.mustune.musicmanager.api.models

sealed class ShareSettings {
    object NoSharing : ShareSettings()
    sealed class Shared(val name: String) : ShareSettings() {
        data class OnlyInvited(val allowOtherToShare: Boolean = true) :
            Shared("only invited people")

        object AnyOneWithLink : Shared("anyone with the link")
        object AllUsers : Shared("all users")
    }
}