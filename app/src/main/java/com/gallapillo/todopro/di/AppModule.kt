package com.gallapillo.todopro.di

import android.app.Application
import androidx.room.Room
import com.gallapillo.todopro.data.local.TodoDatabase
import com.gallapillo.todopro.data.local.repository.TodoRepository
import com.gallapillo.todopro.domain.repository.TodoRepositoryImpl
import com.gallapillo.todopro.domain.use_case.AddTodoUseCase
import com.gallapillo.todopro.domain.use_case.GetTodosUseCase
import com.gallapillo.todopro.domain.use_case.RemoveTodoUseCase
import com.gallapillo.todopro.domain.use_case.TodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application) : TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.todoDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: TodoRepository) : TodoUseCase {
        return TodoUseCase(
            getTodos = GetTodosUseCase(repository),
            deleteTodos = RemoveTodoUseCase(repository),
            addTodo = AddTodoUseCase(repository)
        )
    }
}
