package com.projectlab.travelin_android.presentation.validation

import android.util.Patterns

object AuthValidator {
    private val LOWERCASE = Regex("[a-z]")
    private val UPPERCASE = Regex("[A-Z]")
    private val DIGIT = Regex("\\d")

    fun isEmailValid(email: String): Boolean {
        return email.trim().matches(Patterns.EMAIL_ADDRESS.toRegex())
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
            && password.contains(LOWERCASE)
            && password.contains(UPPERCASE)
            && password.contains(DIGIT)
    }

    fun isAgeValid(age: String): Boolean {
        return age.toIntOrNull()?.let { it in 1..120 } == true
    }
}
