package com.dave_devs.firebaseauthwithdatastore.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dave_devs.firebaseauthwithdatastore.data.repo.FirebaseAuthRepositoryImpl
import com.dave_devs.firebaseauthwithdatastore.domain.resource.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: FirebaseAuthRepositoryImpl
): ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signUpFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow


    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null) {
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

    //For Email login
    fun login(email: String, password: String) {
        viewModelScope.launch {
          _loginFlow.value = Resource.Loading()
            val result = repository.login(email, password)
            _loginFlow.value = result
        }
    }
    //For Email signUp
    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            _signUpFlow.value = Resource.Loading()
            val result = repository.signUp(name, email, password)
            _signUpFlow.value = result
        }
    }

    fun logout() {
        repository.logOut()
        _loginFlow.value = null
        _signUpFlow.value = null
    }
}