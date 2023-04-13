package com.dave_devs.firebaseauthwithdatastore.presentation.authentication

data class SignInState(
    val isError: String? = null,
    val isSignInSuccess: Boolean = false
)