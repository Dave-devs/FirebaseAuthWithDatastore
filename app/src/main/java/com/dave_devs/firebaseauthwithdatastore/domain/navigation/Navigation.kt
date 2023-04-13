package com.dave_devs.firebaseauthwithdatastore.domain.navigation

import androidx.compose.runtime.Composable

@Composable
fun Navigation(
    viewModel: AuthViewModel,
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
            LoginScreen(viewModel, navController = navController)
        }
        composable(SIGN_UP_SCREEN) {
            SignUpScreen(viewModel, navController = navController)
        }
        composable(HOME_SCREEN) {
            HomeScreen(viewModel, navController = navController)
        }
    }
}