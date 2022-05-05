package com.gallapillo.todopro.presentation

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gallapillo.todopro.common.Constants
import com.gallapillo.todopro.common.Constants.TYPE_DATABASE
import com.gallapillo.todopro.domain.model.Todo
import com.gallapillo.todopro.domain.use_case.TodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoUseCase: TodoUseCase
) : ViewModel() {
    private val _state = mutableStateOf(TodoState())
    val state: State<TodoState> = _state

    private var recentlyDeletedNote: Todo? = null

    private var getNotesJob: Job? = null


    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            todoUseCase.addTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoUseCase.deleteTodos(todo)
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

    fun updateTodo(todo: Todo, onSuccess: () -> Unit) {
        viewModelScope.launch {
            todoUseCase.updateTodo(todo)
            viewModelScope.launch(Dispatchers.IO) {
                onSuccess()
            }
        }
    }

    fun deleteTodo(todo: Todo, onSuccess: () -> Unit) {
        viewModelScope.launch {
            todoUseCase.deleteTodos(todo)
            viewModelScope.launch(Dispatchers.IO) {
                onSuccess()
            }
        }
    }
}