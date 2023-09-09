package com.alex.myapplication.domain.use_case

import com.alex.myapplication.R
import com.alex.myapplication.utils.UiText

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_repeated_password_wrong)
            )
        }
        return ValidationResult(successful = true)
    }
}