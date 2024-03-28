package com.todoapp.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todoapp.data.TodoEntity
import com.todoapp.presentation.util.TodoEvent
import com.todoapp.repository.TodoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TodosViewModel : ViewModel(), KoinComponent {
    private val repo: TodoRepo by inject()

    private val _todos: MutableStateFlow<List<TodoEntity>> = MutableStateFlow(emptyList())
    val todos = _todos.asStateFlow()

    var lastDeletedTodo: TodoEntity? = null

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getTodos().collect { data ->
                _todos.update { data }
            }
        }
    }


    fun onEvent(event: TodoEvent) {
        when (event) {
            is TodoEvent.AddTodo -> {
               viewModelScope.launch(Dispatchers.IO) {
                   repo.upsert(event.todo)

               }
            }

            is TodoEvent.ChangeCompletion -> {
                viewModelScope.launch(Dispatchers.IO) {
                    repo.upsert(
                        event.todo.copy(
                            done = !event.todo.done
                        )
                    )
                }
            }

            is TodoEvent.DeleteTodo -> {
                viewModelScope.launch(Dispatchers.IO) {
                    repo.deleteTodo(event.todo)
                    lastDeletedTodo = event.todo
                }
            }

            TodoEvent.RestoreTodo -> {
                viewModelScope.launch(Dispatchers.IO) {
                    lastDeletedTodo?.let { repo.upsert(it) }
                }
            }
        }
    }
}