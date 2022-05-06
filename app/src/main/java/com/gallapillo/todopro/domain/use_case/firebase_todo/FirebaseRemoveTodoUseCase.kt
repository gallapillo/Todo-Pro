package com.gallapillo.todopro.domain.use_case.firebase_todo

import com.gallapillo.todopro.data.firebase.repository.FirebaseTodoRepository
import com.gallapillo.todopro.domain.model.Todo
import javax.inject.Inject

class FirebaseRemoveTodoUseCase @Inject constructor(
    private val repository: FirebaseTodoRepository
) {
    suspend operator fun invoke(todo: Todo, onSuccess: () -> Unit) {
        repository.removeTodoFirebase(todo, onSuccess)
    }
}