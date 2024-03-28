package com.todoapp

import android.app.Application
import androidx.room.Room
import com.todoapp.data.TodoDatabase
import com.todoapp.repository.TodoRepo
import com.todoapp.repository.TodoRepoImpl
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module {
                single {
                    Room.databaseBuilder(
                        this@KoinApp,
                        TodoDatabase::class.java,
                        "todo_db"
                    ).build()
                }
                single {
                    TodoRepoImpl(
                        database = get()
                    )
                } bind TodoRepo::class
            })
        }
    }
}