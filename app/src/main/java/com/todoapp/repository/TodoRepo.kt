package com.todoapp.repository

import com.todoapp.data.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepo {
    suspend fun getTodos(): Flow<List<TodoEntity>>
    suspend fun upsert(todo: TodoEntity)
    suspend fun deleteTodo(todo: TodoEntity)
    suspend fun getTodoById(id: Int): TodoEntity?
}