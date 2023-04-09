package com.dave_devs.firebaseauthwithdatastore.domain.repo

import com.google.firebase.auth.FirebaseUser
import io.grpc.internal.SharedResourceHolder.Resource

interface FirebaseAuthRepository {
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signUp(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logOut()
    val currentUser: FirebaseUser?
}