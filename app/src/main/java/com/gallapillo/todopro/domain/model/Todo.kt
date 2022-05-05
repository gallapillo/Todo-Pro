package com.gallapillo.todopro.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity
data class Todo (
    val title: String,
    val subtitle: String,
    val color: Int = Random.nextInt(0, 3),
    @PrimaryKey val id: Int? = null
)