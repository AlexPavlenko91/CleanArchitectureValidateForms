package com.alex.myapplication.domain.use_case

import android.util.Patterns
import com.alex.myapplication.R
import com.alex.myapplication.utils.UiText

class ValidatePassword {

    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_password_short)
            )
        }

        // todo:: remake with an interface if there is enough time
        val passwordValid = password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!passwordValid) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_password_invalid)
            )
        }
        return ValidationResult(successful = true)
    }

}