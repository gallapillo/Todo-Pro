package com.gallapillo.todopro.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallapillo.todopro.common.Constants.TYPE_FIREBASE
import com.gallapillo.todopro.common.Constants.TYPE_ROOM
import com.gallapillo.todopro.domain.model.Todo
import com.gallapillo.todopro.domain.use_case.database.TodoUseCase
import com.gallapillo.todopro.domain.use_case.firebase.FirebaseUseCase
import com.gallapillo.todopro.domain.use_case.firebase_todo.FirebaseTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoUseCase: TodoUseCase,
    private val firebaseUseCase: FirebaseUseCase,
    private val firebaseTodoUseCase: FirebaseTodoUseCase
) : ViewModel() {
    private val _state = mutableStateOf(TodoState())
    val state: State<TodoState> = _state

    private var getNotesJob: Job? = null


    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            todoUseCase.addTodo(todo)
        }
    }

    fun addTodoFirebase(todo: Todo, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseTodoUseCase.addTodoUseCase(todo)
            viewModelScope.launch(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    fun getTodoFromDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                getNotesJob?.cancel()
                getNotesJob = todoUseCase.getTodos().onEach {  todos ->
                    _state.value = state.value.copy(
                        todos = todos,
                        dbType = TYPE_ROOM
                    )
                }.launchIn(viewModelScope)
                onSuccess()
            }
        }
    }

    fun getTodoFromFirebase(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseUseCase.firebaseConnect(email, password, {
            onSuccess()
            getTodoFromFirebaseDatabase()
        }, {
            onFailure
        })
    }

    fun updateTodo(todo: Todo, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            todoUseCase.updateTodo(todo)
            viewModelScope.launch(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    private fun getTodoFromFirebaseDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            getNotesJob?.cancel()
            getNotesJob = firebaseTodoUseCase.getAllTodoUseCase().onEach {  todos ->
                _state.value = state.value.copy(
                    todos = todos,
                    dbType = TYPE_ROOM
                )
            }.launchIn(viewModelScope)
        }
    }

    fun deleteTodo(todo: Todo, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            todoUseCase.deleteTodos(todo)
            viewModelScope.launch(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    fun removeFirebaseTodo(todo: Todo, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseTodoUseCase.removeTodoUseCase(todo) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateFirebaseTodo(todo: Todo, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseTodoUseCase.updateTodoUseCase(todo) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun signOut(dbType: String, onSuccess: () -> Unit) {
        when (dbType) {
            TYPE_ROOM -> {

            }
            TYPE_FIREBASE -> {
                firebaseUseCase.firebaseSignOut()
                onSuccess()
            }
            else -> {
                Log.d("Exit", "Exit form database else")
            }
        }
    }
}