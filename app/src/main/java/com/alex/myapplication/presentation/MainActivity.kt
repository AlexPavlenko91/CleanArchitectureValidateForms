package com.alex.myapplication.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alex.myapplication.R
import com.alex.myapplication.ui.theme.MyApplicationTheme
import com.alex.myapplication.utils.UiText


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fontFamily = FontFamily(
            Font(R.font.lexend_bold, FontWeight.Bold),
            Font(R.font.lexend_extralight, FontWeight.ExtraLight),
            Font(R.font.lexend_light, FontWeight.Light),
            Font(R.font.lexend_medium, FontWeight.Medium),
            Font(R.font.lexend_semibold, FontWeight.SemiBold),
            Font(R.font.lexend_thin, FontWeight.Thin),
        )
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val viewModel = viewModel<MainViewModel>()
                val state = viewModel.state
                val context = LocalContext.current
                LaunchedEffect(key1 = context) {
                    viewModel.validationEvents.collect { event ->
                        when (event) {
                            is MainViewModel.ValidationEvent.Success -> {
                                Toast.makeText(
                                    context,
                                    UiText.StringResource(R.string.reg_success).asString(context),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = state.email,
                        onValueChange = {
                            viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))
                        },
                        isError = state.emailError != null,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Email")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )
                    if (state.emailError != null) {
                        Text(
                            text = state.emailError.asString(context),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = state.password,
                        onValueChange = {
                            viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
                        },
                        isError = state.passwordError != null,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Password")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    if (state.passwordError != null) {
                        Text(
                            text = state.passwordError.asString(context),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = state.repeatedPassword,
                        onValueChange = {
                            viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
                        },
                        isError = state.repeatedPasswordError != null,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Repeat password")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    if (state.repeatedPasswordError != null) {
                        Text(
                            text = state.repeatedPasswordError.asString(context),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = state.acceptedTerms,
                            onCheckedChange = {
                                viewModel.onEvent(RegistrationFormEvent.AcceptTerms(it))
                            },
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = UiText.StringResource(R.string.accept_terms).asString())
                    }
                    if (state.termsError != null) {
                        Text(
                            text = state.termsError.asString(),
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    Button(
                        onClick = { viewModel.onEvent(RegistrationFormEvent.Submit) },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Підтвердити")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

/*
fun main(){
    val items = listOf(1, -2 ,-3 , 4, 5, 0, 2, -2)

    val countNegatives = items.filter(){ item -> item < 0}.count()

    val negativeSum = items.filter(){ item -> item < 0}.sum()
}*/
