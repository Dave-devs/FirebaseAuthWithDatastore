package com.dave_devs.firebaseauthwithdatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.dave_devs.firebaseauthwithdatastore.domain.navigation.Navigation
import com.dave_devs.firebaseauthwithdatastore.presentation.authentication.AuthViewModel
import com.dave_devs.firebaseauthwithdatastore.presentation.ui.theme.FirebaseAuthWithDatastoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseAuthWithDatastoreTheme {
                Navigation(viewModel)
            }
        }
    }
}