package com.dave_devs.firebaseauthwithdatastore.data.repo

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.dave_devs.firebaseauthwithdatastore.R
import com.dave_devs.firebaseauthwithdatastore.data.SignedInResult
import com.dave_devs.firebaseauthwithdatastore.data.UserData
import com.dave_devs.firebaseauthwithdatastore.domain.repo.FirebaseGoogleAuthRepo
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class FirebaseGoogleAuthRepoImpl @Inject constructor(
    private val context: Context,
    private val oneTapClient: SignInClient,
    private val firebaseAuth: FirebaseAuth,
): FirebaseGoogleAuthRepo {

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