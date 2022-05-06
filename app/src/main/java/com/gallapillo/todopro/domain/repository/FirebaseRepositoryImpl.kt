package com.gallapillo.todopro.domain.repository

import com.gallapillo.todopro.data.firebase.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
): FirebaseRepository {
    override fun connectToFirebase(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFailure(it.message.toString()) }
            }
    }

    override fun firebaseSignOut() {
        auth.signOut()
    }

}