package com.gallapillo.todopro.domain.use_case.firebase_todo

data class FirebaseTodoUseCase (
    val getAllTodoUseCase: FirebaseGetAllTodoUseCase,
    val addTodoUseCase: FirebaseAddTodoUseCase
)