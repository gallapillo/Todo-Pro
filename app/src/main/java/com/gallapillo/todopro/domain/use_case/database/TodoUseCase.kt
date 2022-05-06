package com.gallapillo.todopro.domain.use_case.database

data class TodoUseCase (
    val getTodos: GetTodosUseCase,
    val deleteTodos: RemoveTodoUseCase,
    val addTodo: AddTodoUseCase,
    val updateTodo: UpdateTodoUseCase
)