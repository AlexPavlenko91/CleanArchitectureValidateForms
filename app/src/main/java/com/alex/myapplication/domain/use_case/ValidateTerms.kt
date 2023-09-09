package com.alex.myapplication.domain.use_case

import com.alex.myapplication.R
import com.alex.myapplication.utils.UiText

class ValidateTerms {

    fun execute(acceptedTerms: Boolean): ValidationResult {
        if (!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_terms)
            )
        }
        return ValidationResult(successful = true)
    }
}