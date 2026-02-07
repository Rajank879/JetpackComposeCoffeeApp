package com.rajan.CoffeeShop.presentation.screen.loginscreen

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rajan.CoffeeShop.R
import com.rajan.CoffeeShop.presentation.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val navigateHome = {
        scope.launch {
//            OnboardingDataStore.setHasSeenWelcome(context, true)
            navController.navigate(Routes.HomeScreen) {
                popUpTo(Routes.LoginScreen) { inclusive = true }
            }
        }
    }

    UserClick(
        onLoginClick = {
            navigateHome()
        },
        onGoogleLogin = {
            navigateHome()
        },
        onAppleLogin = {
            navigateHome()
        },
        onSignUpClick = {},
        onForgotPasswordClick = {},
        pwdVisibility = passwordVisibility
    )

}

@Composable
fun UserClick(
    onLoginClick: () -> Unit,
    onGoogleLogin: () -> Unit,
    onAppleLogin: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    pwdVisibility: MutableState<Boolean>
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val emailInput = rememberSaveable { mutableStateOf("") }
                val isEmailValid = remember(emailInput.value) {
                    Patterns.EMAIL_ADDRESS.matcher(emailInput.value).matches()
                }
                val showEmailError = emailInput.value.isNotEmpty() && !isEmailValid

                val passwordInput = remember { mutableStateOf("") }
                val isPasswordValid = passwordInput.value.length >= 6
                val showPasswordError = passwordInput.value.isNotEmpty() && !isPasswordValid


                val visualTransformation =
                    if (pwdVisibility.value) VisualTransformation.None else PasswordVisualTransformation()

                //Header section
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    text = stringResource(R.string.login),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.header_text),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))

                // TextField
                OutlinedTextField(
                    value = emailInput.value,
                    onValueChange = { emailInput.value = it },
                    label = { Text(text = "Email") },
                    isError = showEmailError,
                    supportingText = {
                        if (showEmailError) {
                            Text(
                                text = "Enter a valid email",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    },
                    trailingIcon = {
                        Surface(
                            modifier = Modifier.size(40.dp),
                            shape = CircleShape,
                            color = Color.White
                        )
                        {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "email icon",
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(100.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                //Password TextField
                OutlinedTextField(
                    value = passwordInput.value,
                    onValueChange = { passwordInput.value = it },
                    label = { Text(text = "Password") },
                    isError = showPasswordError,
                    supportingText = {
                        if (showPasswordError) {
                            Text(
                                text = "Password should be at least 6 characters",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    },
                    visualTransformation = visualTransformation,
                    trailingIcon = {
                        Surface(
                            modifier = Modifier.size(40.dp),
                            shape = CircleShape,
                            color = Color.White
                        )
                        {
                            Box(contentAlignment = Alignment.Center) {
                                IconButton(
                                    onClick = { pwdVisibility.value = !pwdVisibility.value },
                                    modifier = Modifier.size(24.dp)
                                )
                                {
                                    Icon(
                                        imageVector = if (pwdVisibility.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = null,
                                        tint = Color.Unspecified
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(100.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
                // Remember me section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val checkedState = remember { mutableStateOf(false) }
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary,
                            uncheckedColor = MaterialTheme.colorScheme.onSurface,
                            checkmarkColor = MaterialTheme.colorScheme.onPrimary,
                        )
                    )
                    Text(
                        text = "Remember me",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)

                    )
                    ClickableText(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Green,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Forgot Password?")
                            }
                        }, onClick = { onForgotPasswordClick() }
                    )
                }
                //Login Button
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        when {
                            emailInput.value.isEmpty() && passwordInput.value.isEmpty() -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Please enter email and password")
                                }
                            }

                            emailInput.value.isEmpty() -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Please enter email")
                                }
                            }

                            passwordInput.value.isEmpty() -> {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Please enter password")
                                }
                            }

                            else -> onLoginClick()

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))

                // or Text
                Text(
                    text = "Or",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(32.dp))

                //Social Login Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ElevatedButton(
                        onClick = onGoogleLogin,
                        modifier = Modifier.height(48.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color.White

                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.google),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Google",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }

                    ElevatedButton(
                        onClick = onAppleLogin,
                        modifier = Modifier.height(48.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color.White

                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.apple_logo),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Apple",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                // Signup prompt
                val signupText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append("Do not have an account yet? ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(" Sign Up")
                    }
                }

                ClickableText(text = signupText, onClick = { onSignUpClick })
            }
        }
    }


}

