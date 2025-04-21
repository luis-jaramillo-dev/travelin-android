package com.projectlab.auth.data

import kotlinx.coroutines.flow.MutableStateFlow

class LoginFirebaseViewModel (){

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String){
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun onSingInClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching
    }
}