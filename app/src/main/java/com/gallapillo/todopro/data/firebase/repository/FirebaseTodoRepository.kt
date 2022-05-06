package com.gallapillo.todopro.data.firebase.repository

import androidx.lifecycle.LiveData
import com.gallapillo.todopro.domain.model.Todo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow

interface FirebaseTodoRepository {

    fun getAllTodoFromFirebase(): Flow<List<Todo>>

    suspend fun addTodoFirebase(todo: Todo, onSuccess: () -> Unit)

    fun updateTodoFirebase(todo: Todo, onSuccess: () -> Unit)

    fun removeTodoFirebase(todo: Todo, onSuccess: () -> Unit)
}