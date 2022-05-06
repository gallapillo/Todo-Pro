package com.gallapillo.todopro.presentation

import com.gallapillo.todopro.common.Constants
import com.gallapillo.todopro.common.Constants.TYPE_ROOM
import com.gallapillo.todopro.domain.model.Todo

data class TodoState (
    val todos: List<Todo> = emptyList(),
    val dbType: String = TYPE_ROOM
)