package com.gallapillo.todopro.domain.use_case.database

import com.gallapillo.todopro.data.local.repository.TodoRepository
import com.gallapillo.todopro.domain.model.Todo
import java.io.InvalidClassException
import kotlin.jvm.Throws

class AddTodoUseCase(
    private val repository: TodoRepository
) {
    @Throws(InvalidClassException::class)
    suspend operator fun invoke(todo: Todo) {
        if (todo.title.isBlank()) {
            throw InvalidClassException("The title is empty")
        }
        if (todo.subtitle.isBlank()) {
            throw InvalidClassException("The subtitle is empty")
        }
        repository.insertTodo(todo)
    }
}