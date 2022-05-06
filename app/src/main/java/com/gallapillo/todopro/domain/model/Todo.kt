package com.gallapillo.todopro.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity
data class Todo (
    val title: String = "Hello",
    val subtitle: String = "World",
    val color: Int = Random.nextInt(0, 8),
    val firebaseId: String? = null,
    @PrimaryKey val id: Int? = null
)