package com.projectlab.travelin_android.presentation.validation

import android.util.Patterns

object AuthValidator {

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        // Minimum of 6 characters, one letter uppercase and lowercase and a number
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}\$")
        return regex.matches(password)
    }

    fun isAgeValid(age: String): Boolean {
        return age.toIntOrNull()?.let { it in 1..120 } ?: false
    }

    fun isTermsAccepted(accepted: Boolean): Boolean {
        return accepted
    }
}