package com.dave_devs.firebaseauthwithdatastore.di

import com.dave_devs.firebaseauthwithdatastore.data.repo.FirebaseAuthRepositoryImpl
import com.dave_devs.firebaseauthwithdatastore.domain.repo.FirebaseAuthRepository
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
    fun provideFirebaseAuthRepository(firebaseAuth: FirebaseAuth): FirebaseAuthRepository {
        return FirebaseAuthRepositoryImpl(firebaseAuth)
    }
}