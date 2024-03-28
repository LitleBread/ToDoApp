package com.todoapp.repository

import com.todoapp.data.TodoDatabase
import com.todoapp.data.TodoEntity
import kotlinx.coroutines.flow.Flow

class TodoRepoImpl(
    private val database: TodoDatabase
): TodoRepo {
    private val dao = database.todoDao()
    override suspend fun getTodos(): Flow<List<TodoEntity>> = dao.getTodos()

    override suspend fun upsert(todo: TodoEntity) = dao.upsert(todo)

    override suspend fun deleteTodo(todo: TodoEntity) = dao.delete(todo)

    override suspend fun getTodoById(id: Int): TodoEntity? = dao.getTodoById(id)
}