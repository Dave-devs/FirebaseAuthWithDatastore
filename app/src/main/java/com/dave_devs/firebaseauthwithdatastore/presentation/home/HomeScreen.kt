package com.dave_devs.firebaseauthwithdatastore.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dave_devs.firebaseauthwithdatastore.R
import com.dave_devs.firebaseauthwithdatastore.domain.navigation.Routes.HOME_SCREEN
import com.dave_devs.firebaseauthwithdatastore.domain.navigation.Routes.LOGIN_SCREEN
import com.dave_devs.firebaseauthwithdatastore.presentation.authentication.AuthViewModel
import com.dave_devs.firebaseauthwithdatastore.presentation.login.LoginScreen
import com.dave_devs.firebaseauthwithdatastore.presentation.ui.theme.FirebaseAuthWithDatastoreTheme

@Composable
fun HomeScreen(
    viewModel: AuthViewModel?,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.greeting),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_placeholder_image),
                contentDescription = stringResource(id = R.string.place_holder),
                modifier = Modifier.size(128.dp)
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = viewModel?.currentUser?.displayName ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = viewModel?.currentUser?.email ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(10.dp))
            Button(onClick = {
                viewModel?.logout()
                navController.navigate(LOGIN_SCREEN) {
                    popUpTo(HOME_SCREEN) {inclusive = true}
                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = stringResource(id = R.string.logout),
                    fontStyle = MaterialTheme.typography.displayMedium.fontStyle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {
    FirebaseAuthWithDatastoreTheme {
        LoginScreen(null, navController = rememberNavController())
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewDark() {
    FirebaseAuthWithDatastoreTheme {
        LoginScreen(null, navController = rememberNavController())
    }

}