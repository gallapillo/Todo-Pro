package com.gallapillo.todopro.domain.repository

import com.gallapillo.todopro.data.firebase.repository.FirebaseTodoRepository
import com.gallapillo.todopro.domain.model.Todo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseTodoRepositoryImpl @Inject constructor(
    private val firebaseDatabase : Firebase,
    private val auth: FirebaseAuth
) : FirebaseTodoRepository {
    override fun getAllTodoFromFirebase(): Flow<List<Todo>> = callbackFlow {
        val database = firebaseDatabase.database.reference
            .child(auth.currentUser?.uid.toString())

        val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val todos = mutableListOf<Todo>()
                    snapshot.children.map {
                        todos.add(it.getValue(Todo::class.java) ?: Todo("H", "W"))
                    }
                    trySend(todos)
                }
                override fun onCancelled(error: DatabaseError) { }
        }

        database.addValueEventListener(listener)

        awaitClose {
            database.removeEventListener(listener)
        }
    }

    override suspend fun addTodoFirebase(todo: Todo, onSuccess: () -> Unit) {
        val database = firebaseDatabase.database.reference
            .child(auth.currentUser?.uid.toString())
        val todoId = database.push().key.toString()
        val mapTodos = hashMapOf<String, Any>()

        mapTodos["firebaseId"] = todoId
        mapTodos["title"] = todo.title
        mapTodos["subtitle"] = todo.subtitle
        mapTodos["color"] = todo.color

        database.child(todoId)
            .updateChildren(mapTodos)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {

            }
    }

    override fun updateTodoFirebase(todo: Todo, onSuccess: () -> Unit) {
        // TODO("Not yet implemented")
    }

    override fun removeTodoFirebase(todo: Todo, onSuccess: () -> Unit) {
        // TODO("Not yet implemented")
    }

}