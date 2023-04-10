package com.dave_devs.firebaseauthwithdatastore.domain.repo

import android.content.Intent
import android.content.IntentSender
import com.dave_devs.firebaseauthwithdatastore.data.SignedInResult
import com.dave_devs.firebaseauthwithdatastore.domain.resource.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    suspend fun login(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun signUp(name: String, email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun getSignInResultWithIntent(intent: Intent): SignedInResult
    suspend fun signInWithGoogleIntent(): IntentSender?
    fun logOut()
    val currentUser: FirebaseUser?
}