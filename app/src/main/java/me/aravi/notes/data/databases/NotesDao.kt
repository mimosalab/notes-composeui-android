package me.aravi.notes.data.databases

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.aravi.notes.beans.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getEasyNotes(): Flow<List<Notes>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getEasyNotesById(id: Int): Notes?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEasyNotes(note: Notes)

    @Delete
    suspend fun deleteEasyNotes(note: Notes)
}