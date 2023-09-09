package com.alex.myapplication.presentation

import com.alex.myapplication.utils.UiText

data class RegistrationFromState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: UiText? = null,
    val acceptedTerms: Boolean = false,
    val termsError: UiText? = null
)