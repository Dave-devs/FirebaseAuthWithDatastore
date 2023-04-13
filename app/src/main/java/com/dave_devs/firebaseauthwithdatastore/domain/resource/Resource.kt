package com.dave_devs.firebaseauthwithdatastore.domain.resource

sealed class Resource<T>(open var data: T? = null, val exception: Exception? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Failure<T>(exception: Exception, data: T? = null): Resource<T>(data, exception)
    class Loading<T>(data: T? = null): Resource<T>(data)
}
