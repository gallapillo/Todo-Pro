package com.gallapillo.todopro.domain.use_case

data class TodoUseCase (
    val getTodos: GetTodosUseCase,
    val deleteTodos: RemoveTodoUseCase,
    val addTodo: AddTodoUseCase
)