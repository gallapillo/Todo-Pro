package com.gallapillo.todopro.domain.use_case

import com.gallapillo.todopro.data.local.repository.TodoRepository
import com.gallapillo.todopro.domain.model.Todo
import kotlinx.coroutines.flow.Flow

class GetTodosUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(): Flow<List<Todo>> {
        return repository.getTodos()
    }
}