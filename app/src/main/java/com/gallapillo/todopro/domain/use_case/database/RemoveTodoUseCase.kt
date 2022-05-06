package com.gallapillo.todopro.domain.use_case.database

import com.gallapillo.todopro.data.local.repository.TodoRepository
import com.gallapillo.todopro.domain.model.Todo

class RemoveTodoUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) {
        repository.deleteTodo(todo)
    }
}