package com.dave_devs.firebaseauthwithdatastore.data.repo

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.dave_devs.firebaseauthwithdatastore.R
import com.dave_devs.firebaseauthwithdatastore.data.SignedInResult
import com.dave_devs.firebaseauthwithdatastore.data.UserData
import com.dave_devs.firebaseauthwithdatastore.domain.repo.FirebaseAuthRepository
import com.dave_devs.firebaseauthwithdatastore.domain.resource.Resource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val context: Context,
    private val oneTapClient: SignInClient
): FirebaseAuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }. catch {
            emit(Resource.Failure(it.message.toString()))
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            emit(Resource.Success(result))
        }. catch {
            emit(Resource.Failure(it.message.toString()))
        }
    }

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override fun logOut() {
        firebaseAuth.signOut()
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            firebaseAuth.signOut()
        } catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = firebaseAuth.currentUser?.run {
        UserData(
            userId = uid,
            userName = displayName,
            profilePic = photoUrl?.toString()
        )
    }

    override suspend fun signInWithGoogleIntent(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    override suspend fun getSignInResultWithIntent(intent: Intent): SignedInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = firebaseAuth.signInWithCredential(googleCredentials).await().user
            SignedInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        userName = displayName,
                        profilePic = photoUrl?.toString()
                    )
                },
                error = null
            )
        } catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignedInResult(
                data = null,
                error = e.message
            )
        }
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}