package com.gallapillo.todopro.presentation

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallapillo.todopro.common.Constants
import com.gallapillo.todopro.domain.model.Todo
import com.gallapillo.todopro.domain.use_case.TodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun getTodoFromDatabase() {
        getNotesJob?.cancel()
        getNotesJob = todoUseCase.getTodos().onEach {  todos ->
            _state.value = state.value.copy(
                todos = todos,
                dbType = Constants.TYPE_DATABASE
            )
        }.launchIn(viewModelScope)
    }
}