package com.gallapillo.todopro.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    val title: String,
    val subtitle: String,
    @PrimaryKey val id: Int? = null
)