package com.dave_devs.firebaseauthwithdatastore.domain.resource

sealed class Resource<T>(open val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Failure<T>(message: String?, data: T? = null): Resource<T>(data, message)
    class Loading<T>(data: T? = null): Resource<T>(data)
}
