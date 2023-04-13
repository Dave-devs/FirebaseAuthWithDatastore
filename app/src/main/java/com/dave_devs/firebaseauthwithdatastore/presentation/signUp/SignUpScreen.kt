package com.dave_devs.firebaseauthwithdatastore.presentation.signUp

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dave_devs.firebaseauthwithdatastore.R
import com.dave_devs.firebaseauthwithdatastore.domain.navigation.Routes
import com.dave_devs.firebaseauthwithdatastore.domain.navigation.Routes.LOGIN_SCREEN
import com.dave_devs.firebaseauthwithdatastore.domain.resource.Resource
import com.dave_devs.firebaseauthwithdatastore.presentation.authentication.AuthViewModel
import com.dave_devs.firebaseauthwithdatastore.presentation.ui.theme.FirebaseAuthWithDatastoreTheme

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel?,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val (checkedState, onStateChange) = remember { mutableStateOf(true) }
    val context = LocalContext.current
    var name by remember {mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}

    val signUpFlow = viewModel?.signUpFlow?.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 20.dp, end = 20.dp)
    ){
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                fontStyle = MaterialTheme.typography.displayLarge.fontStyle,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = stringResource(id = R.string.sign_up_explore),
                fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = stringResource(id = R.string.google_logo),
                    modifier = Modifier
                        .clickable {

                        }
                        .size(50.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.facebook_logo),
                    contentDescription = stringResource(id = R.string.facebook_logo),
                    modifier = Modifier
                        .clickable {

                        }
                        .size(50.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.twitter_logo),
                    contentDescription = stringResource(id = R.string.twitter_logo),
                    modifier = Modifier
                        .clickable {

                        }
                        .size(50.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                )
            }
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(thickness = 1.dp)
            }
        }
        Spacer(Modifier.height(6.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.name),
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.height(4.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.placeholder_name),
                        fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                        fontWeight = FontWeight.Medium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.email),
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.height(4.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.placeholder_email),
                        fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                        fontWeight = FontWeight.Medium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.password),
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.height(4.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.placeholder_password),
                        fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                        fontWeight = FontWeight.Medium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .toggleable(
                value = checkedState,
                onValueChange = { onStateChange(! checkedState) },
                role = Role.Checkbox
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = null
            )
            Spacer(Modifier.width(5.dp))
            Text(
                text = stringResource(id = R.string.terms_cond)
            )
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
                viewModel?.signUp(name, email, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = stringResource(id = R.string.get_started),
                fontStyle = MaterialTheme.typography.displayMedium.fontStyle,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        Spacer(Modifier.height(20.dp))
        Divider(thickness = 0.5.dp)
        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.have_acct),
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.width(5.dp))
            Text(
                text = stringResource(id = R.string.login),
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable{
                       navController.navigate(LOGIN_SCREEN)
                    }
            )
        }
    }
    signUpFlow?.value?.let {
        when(it) {
            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.HOME_SCREEN) {
                        popUpTo(Routes.HOME_SCREEN) {inclusive = true}
                    }
                }
            }
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Failure -> {
                Toast.makeText(
                    context,
                    it.exception?.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun SignUpScreenPreview() {
    FirebaseAuthWithDatastoreTheme {
        SignUpScreen(null, navController = rememberNavController())
    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignUpScreenPreviewDark() {
    FirebaseAuthWithDatastoreTheme {
        SignUpScreen(null, navController = rememberNavController())
    }

}