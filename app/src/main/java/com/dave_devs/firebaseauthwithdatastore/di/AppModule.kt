package com.dave_devs.firebaseauthwithdatastore.di

import android.app.Application
import com.dave_devs.firebaseauthwithdatastore.data.repo.FirebaseAuthRepositoryImpl
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
    fun provideFirebaseAuthRepository(
        firebaseAuth: FirebaseAuth,
        context: Application,
        oneTapClient: SignInClient
    ): FirebaseAuthRepositoryImpl {
        return FirebaseAuthRepositoryImpl(
            firebaseAuth,
            context,
            oneTapClient
        )
    }
}