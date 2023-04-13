package com.dave_devs.firebaseauthwithdatastore.di

import android.app.Application
import com.dave_devs.firebaseauthwithdatastore.data.repo.FirebaseAuthRepositoryImpl
import com.dave_devs.firebaseauthwithdatastore.data.repo.FirebaseGoogleAuthRepoImpl
import com.dave_devs.firebaseauthwithdatastore.domain.repo.FirebaseGoogleAuthRepo
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth{
        return  FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthRepository( firebaseAuth: FirebaseAuth): FirebaseAuthRepositoryImpl {
        return FirebaseAuthRepositoryImpl(
            firebaseAuth
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseGoogleAuthRepo(
        firebaseAuth: FirebaseAuth,
        context: Application,
        oneTapClient: SignInClient
    ): FirebaseGoogleAuthRepo {
        return FirebaseGoogleAuthRepoImpl(
            context,
            oneTapClient,
            firebaseAuth,
        )
    }
}