package com.gallapillo.todopro.presentation

import com.gallapillo.todopro.common.Constants
import com.gallapillo.todopro.domain.model.Todo

data class TodoState (
    val todos: List<Todo> = emptyList(),
    val dbType: String = Constants.TYPE_DATABASE
)