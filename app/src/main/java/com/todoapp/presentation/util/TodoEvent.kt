package com.todoapp.presentation.util

import com.todoapp.data.TodoEntity

sealed class TodoEvent {
    class AddTodo(val todo: TodoEntity) : TodoEvent()
    class DeleteTodo(val todo: TodoEntity) : TodoEvent()
    class ChangeCompletion(val todo: TodoEntity): TodoEvent()
    data object RestoreTodo: TodoEvent()
}