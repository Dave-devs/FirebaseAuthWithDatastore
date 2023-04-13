package com.dave_devs.firebaseauthwithdatastore.domain.repo

import android.content.Intent
import android.content.IntentSender
import com.dave_devs.firebaseauthwithdatastore.data.SignedInResult

interface FirebaseGoogleAuthRepo {
    suspend fun getSignInResultWithIntent(intent: Intent): SignedInResult
    suspend fun signInWithGoogleIntent(): IntentSender?
}