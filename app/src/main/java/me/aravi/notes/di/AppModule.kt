package me.aravi.notes.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.aravi.notes.data.databases.NotesDatabase
import me.aravi.notes.data.interactors.*
import me.aravi.notes.data.repositories.NotesRepo
import me.aravi.notes.data.repositories.NotesRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEasyNoteDatabase(app: Application): NotesDatabase {
        return Room
            .databaseBuilder(app, NotesDatabase::class.java, NotesDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideEasyNoteRepository(db: NotesDatabase): NotesRepo {
        return NotesRepoImpl(db.easyNotesDao)
    }

    @Provides
    @Singleton
    fun provideEasyNoteUseCases(repository: NotesRepo): NotesInteract {
        return NotesInteract(
            getEasyNotes = GetNotes(repository),
            deleteEasyNotes = DeleteNotes(repository),
            addEasyNotes = AddNotes(repository),
            getEasyNote = GetNote(repository)
        )
    }
}