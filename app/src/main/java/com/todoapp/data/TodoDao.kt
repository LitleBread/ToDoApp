package com.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Upsert
    fun upsert(todo: TodoEntity)

    @Delete
    fun delete(todo: TodoEntity)

    @Query("SELECT * FROM 'todos'")
    fun getTodos(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM 'todos' WHERE id=:id")
    fun getTodoById(id: Int): TodoEntity?
}