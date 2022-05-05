package com.gallapillo.todopro.data.local.repository

import com.gallapillo.todopro.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getTodos(): Flow<List<Todo>>

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)
}