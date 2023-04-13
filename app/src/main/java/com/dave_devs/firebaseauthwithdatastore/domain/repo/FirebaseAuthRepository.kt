package com.dave_devs.firebaseauthwithdatastore.domain.repo

import com.dave_devs.firebaseauthwithdatastore.domain.resource.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signUp(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logOut()
    val currentUser: FirebaseUser?
}