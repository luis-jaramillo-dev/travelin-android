package com.projectlab.travelin_android.ui

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    object Onboarding

    @Serializable
    object Example
}
