package com.dave_devs.firebaseauthwithdatastore.presentation.authentication

import androidx.lifecycle.ViewModel
import com.dave_devs.firebaseauthwithdatastore.data.SignedInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GoogleAuthViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    //For Google Auth
    fun onSignInResult(result: SignedInResult) {
        _state.update { it.copy(
            isSignInSuccess = result.data != null,
            isError = result.error
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}