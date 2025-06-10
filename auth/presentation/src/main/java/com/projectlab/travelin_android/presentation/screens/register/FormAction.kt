package com.projectlab.travelin_android.presentation.screens.register

sealed interface FormAction {
    data class OnFirstNameChange(val value: String): FormAction
    data class OnLastNameChange(val value: String): FormAction
    data class OnCountryCodeChange(val value: String): FormAction
    data class OnPhoneNumberChange(val value: String): FormAction
    data class OnAgeChange(val value: String): FormAction
    data class OnEmailChange(val value: String): FormAction
    data class OnPasswordChange(val value: String): FormAction
    data class OnAcceptedTOSChange(val value: Boolean): FormAction
}
