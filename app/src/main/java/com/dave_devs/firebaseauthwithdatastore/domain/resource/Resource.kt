package com.dave_devs.firebaseauthwithdatastore.domain.resource

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserInfo
import io.grpc.internal.SharedResourceHolder

sealed class Resource<T>(val result: T? = null, val exception: Exception? = null) {

    class Success<T>(result: T?): Resource<T>(result), SharedResourceHolder.Resource<FirebaseUser> {
        override fun create(): FirebaseUser {
            TODO("Not yet implemented")
        }

        override fun close(instance: FirebaseUser?) {
            return
        }
    }

    class Failure<T>(exception: Exception?, result: T? = null): Resource<T>(result, exception),
        SharedResourceHolder.Resource<FirebaseUser> {
        override fun create(): FirebaseUser {
            TODO("Not yet implemented")
        }

        override fun close(instance: FirebaseUser?) {
            return
        }
    }

    class Loading<T>(result: T? = null): Resource<T>(result)



//    data class Success<out R>(val result: R): Resource<R>(),
//        SharedResourceHolder.Resource<FirebaseUser> {
//        override fun create(): FirebaseUser {
//
//        }
//
//        override fun close(instance: FirebaseUser?) {
//
//        }
//    }
//
//    data class Failure(val exception: Exception): Resource<R>()
//    object Loading: Resource<Nothing>()

}
