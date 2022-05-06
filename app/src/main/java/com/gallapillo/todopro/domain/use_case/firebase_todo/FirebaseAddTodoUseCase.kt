package com.gallapillo.todopro.domain.use_case.firebase_todo

import com.gallapillo.todopro.data.firebase.repository.FirebaseTodoRepository
import com.gallapillo.todopro.domain.model.Todo
import java.io.InvalidClassException
import javax.inject.Inject
import kotlin.jvm.Throws

class FirebaseAddTodoUseCase @Inject constructor(
    private val repository: FirebaseTodoRepository
) {
    @Throws(InvalidClassException::class)
    suspend operator fun invoke(todo: Todo) {
        if (todo.title.isBlank()) {
            throw InvalidClassException("The title is empty")
        }
        if (todo.subtitle.isBlank()) {
            throw InvalidClassException("The subtitle is empty")
        }
        repository.addTodoFirebase(todo) {

        }
    }
}