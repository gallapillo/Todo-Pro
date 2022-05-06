package com.gallapillo.todopro.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallapillo.todopro.common.Constants.TYPE_DATABASE
import com.gallapillo.todopro.common.Constants.TYPE_FIREBASE
import com.gallapillo.todopro.domain.model.Todo
import com.gallapillo.todopro.domain.use_case.database.TodoUseCase
import com.gallapillo.todopro.domain.use_case.firebase.FirebaseUseCase
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
    private val firebaseUseCase: FirebaseUseCase
) : ViewModel() {
    private val _state = mutableStateOf(TodoState())
    val state: State<TodoState> = _state

    private var getNotesJob: Job? = null


    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            todoUseCase.addTodo(todo)
        }
    }

    fun getTodoFromDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_DATABASE -> {
                getNotesJob?.cancel()
                getNotesJob = todoUseCase.getTodos().onEach {  todos ->
                    _state.value = state.value.copy(
                        todos = todos,
                        dbType = TYPE_DATABASE
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

    fun deleteTodo(todo: Todo, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            todoUseCase.deleteTodos(todo)
            viewModelScope.launch(Dispatchers.Main) {
                onSuccess()
            }
        }
    }
}