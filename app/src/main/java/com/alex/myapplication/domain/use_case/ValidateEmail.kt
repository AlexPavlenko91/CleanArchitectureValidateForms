package com.alex.myapplication.domain.use_case

import android.util.Patterns
import com.alex.myapplication.R
import com.alex.myapplication.utils.UiText

class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_email_blank)
            )
        }

        // todo:: remake with an interface if there is enough time
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_email_invalid)
            )
        }
        return ValidationResult(successful = true)
    }

}