package com.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date

@Entity("todos")
data class TodoEntity(
    val title: String,
    val content: String,
    val done:Boolean = false,
    val dateCreated: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

val TodoEntity.addDate:String get() = SimpleDateFormat.getDateInstance().format(dateCreated)