package com.gallapillo.todopro.domain.use_case.firebase

import com.gallapillo.todopro.data.firebase.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseConnectUseCase @Inject constructor(
    private val repository: FirebaseRepository
) {
    operator fun invoke(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) = repository.connectToFirebase(email, password, onSuccess, onFailure)
}