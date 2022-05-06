package com.gallapillo.todopro.domain.use_case.firebase_todo

import com.gallapillo.todopro.data.firebase.repository.FirebaseTodoRepository
import com.gallapillo.todopro.domain.model.Todo
import javax.inject.Inject

class FirebaseUpdateTodoUseCase @Inject constructor(
    private val repository: FirebaseTodoRepository
) {
    suspend operator fun invoke(todo: Todo, onSuccess: () -> Unit) {
        repository.updateTodoFirebase(todo) {
            onSuccess()
        }
    }
}