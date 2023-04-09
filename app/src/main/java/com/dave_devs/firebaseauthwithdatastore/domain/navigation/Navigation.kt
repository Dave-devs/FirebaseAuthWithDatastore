package com.dave_devs.firebaseauthwithdatastore.domain.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dave_devs.firebaseauthwithdatastore.domain.navigation.Routes.HOME_SCREEN
import com.dave_devs.firebaseauthwithdatastore.domain.navigation.Routes.LOGIN_SCREEN
import com.dave_devs.firebaseauthwithdatastore.domain.navigation.Routes.SIGN_UP_SCREEN
import com.dave_devs.firebaseauthwithdatastore.presentation.login.LoginScreen
import com.dave_devs.firebaseauthwithdatastore.presentation.signup.SignUpScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = LOGIN_SCREEN
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(LOGIN_SCREEN) {
            LoginScreen(navController = navController)
        }
        composable(SIGN_UP_SCREEN) {
            SignUpScreen(navController = navController)
        }
        composable(HOME_SCREEN) {

        }
    }
}