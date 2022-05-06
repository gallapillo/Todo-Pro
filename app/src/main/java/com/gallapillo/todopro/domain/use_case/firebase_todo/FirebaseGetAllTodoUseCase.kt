package com.gallapillo.todopro.domain.use_case.firebase_todo

import com.gallapillo.todopro.data.firebase.repository.FirebaseTodoRepository
import javax.inject.Inject

class FirebaseGetAllTodoUseCase @Inject constructor(
    private val repository: FirebaseTodoRepository
) {
    operator fun invoke() = repository.getAllTodoFromFirebase()
}