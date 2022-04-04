package me.aravi.notes.beans

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.aravi.notes.ui.theme.*

@Entity(tableName = "notes")
data class Notes(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val easyNotesColors =
            listOf(LightPink, LightYellow, LightBlue, LightGreen, DarkYellow, RedYellow)
    }
}

class InvalidNoteException(message: String) : Exception(message)