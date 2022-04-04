package me.aravi.notes.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import me.aravi.notes.beans.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = true)
abstract class NotesDatabase : RoomDatabase() {

    abstract val easyNotesDao: NotesDao

    companion object {
        const val DATABASE_NAME = "notes.db"
    }
}