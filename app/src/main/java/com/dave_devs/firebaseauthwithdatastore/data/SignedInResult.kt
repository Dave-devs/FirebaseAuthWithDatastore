package com.dave_devs.firebaseauthwithdatastore.data

data class SignedInResult(
    val data: UserData?,
    val error: String?
)

data class UserData (
    val userId: String,
    val userName: String?,
    val profilePic: String?
)


