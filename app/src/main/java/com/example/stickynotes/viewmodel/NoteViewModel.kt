package com.example.stickynotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stickynotes.db.Note
import com.example.stickynotes.repository.NotesRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NotesRepository): ViewModel() {
    val allNotes: LiveData<List<Note>> = repository.getNotes

    fun createNewNote(note: Note) = viewModelScope.launch {
        repository.createNote(note)
    }
}