package com.gallapillo.todopro.domain.use_case.firebase

import com.gallapillo.todopro.data.firebase.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseSignOutUseCase @Inject constructor(
    private val repository: FirebaseRepository
) {
    operator fun invoke() = repository.firebaseSignOut()
}