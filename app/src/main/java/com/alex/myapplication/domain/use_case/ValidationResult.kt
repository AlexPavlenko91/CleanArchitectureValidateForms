package com.alex.myapplication.domain.use_case

import com.alex.myapplication.utils.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)