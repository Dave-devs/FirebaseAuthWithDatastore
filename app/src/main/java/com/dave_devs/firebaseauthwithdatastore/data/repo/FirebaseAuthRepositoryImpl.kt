package com.dave_devs.firebaseauthwithdatastore.data.repo

import com.dave_devs.firebaseauthwithdatastore.domain.repo.FirebaseAuthRepository
import com.dave_devs.firebaseauthwithdatastore.domain.resource.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import io.grpc.internal.SharedResourceHolder
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): FirebaseAuthRepository {

    override suspend fun login(email: String, password: String): SharedResourceHolder.Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e, null)
        }
    }
    override suspend fun signUp(name: String, email: String, password: String): SharedResourceHolder.Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            Resource.Failure(e, null)
        }
    }
    override fun logOut() {
        firebaseAuth.signOut()
    }
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser
}