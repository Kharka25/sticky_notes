package com.example.stickynotes.repository

import androidx.lifecycle.LiveData
import com.example.stickynotes.db.Note
import com.example.stickynotes.db.NoteDao

class NotesRepository(private val noteDao: NoteDao) {
    suspend fun createNote(note: Note) {
        return noteDao.insert(note);
    }

    val getNotes: LiveData<List<Note>> = noteDao.getAllNotes();
}