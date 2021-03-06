package com.gallapillo.todopro.di

import android.app.Application
import androidx.room.Room
import com.gallapillo.todopro.data.firebase.repository.FirebaseRepository
import com.gallapillo.todopro.data.firebase.repository.FirebaseTodoRepository
import com.gallapillo.todopro.data.local.TodoDatabase
import com.gallapillo.todopro.data.local.repository.TodoRepository
import com.gallapillo.todopro.domain.repository.FirebaseRepositoryImpl
import com.gallapillo.todopro.domain.repository.FirebaseTodoRepositoryImpl
import com.gallapillo.todopro.domain.repository.TodoRepositoryImpl
import com.gallapillo.todopro.domain.use_case.database.*
import com.gallapillo.todopro.domain.use_case.firebase.FirebaseConnectUseCase
import com.gallapillo.todopro.domain.use_case.firebase.FirebaseSignOutUseCase
import com.gallapillo.todopro.domain.use_case.firebase.FirebaseUseCase
import com.gallapillo.todopro.domain.use_case.firebase_todo.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
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
    fun provideTodoDatabase(app: Application): TodoDatabase {
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
    fun provideTodoUseCase(repository: TodoRepository): TodoUseCase {
        return TodoUseCase(
            getTodos = GetTodosUseCase(repository),
            deleteTodos = RemoveTodoUseCase(repository),
            addTodo = AddTodoUseCase(repository),
            updateTodo = UpdateTodoUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseDatabase(): Firebase {
        return Firebase
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(auth: FirebaseAuth): FirebaseRepository {
        return FirebaseRepositoryImpl(
            auth = auth
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseUseCase(repository: FirebaseRepository): FirebaseUseCase {
        return FirebaseUseCase(
            firebaseConnect = FirebaseConnectUseCase(repository),
            firebaseSignOut = FirebaseSignOutUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseTodoRepository(firebaseDatabase: Firebase, auth: FirebaseAuth): FirebaseTodoRepository {
        return FirebaseTodoRepositoryImpl(
            firebaseDatabase = firebaseDatabase,
            auth = auth
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseTodoUseCase(repository: FirebaseTodoRepository): FirebaseTodoUseCase {
        return FirebaseTodoUseCase(
            getAllTodoUseCase = FirebaseGetAllTodoUseCase(repository = repository),
            addTodoUseCase = FirebaseAddTodoUseCase(repository = repository),
            updateTodoUseCase = FirebaseUpdateTodoUseCase(repository = repository),
            removeTodoUseCase = FirebaseRemoveTodoUseCase(repository = repository)
        )
    }
}
