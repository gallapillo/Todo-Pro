package com.gallapillo.todopro.data.firebase.repository

interface FirebaseRepository {
    fun connectToFirebase(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun firebaseSignOut()
}