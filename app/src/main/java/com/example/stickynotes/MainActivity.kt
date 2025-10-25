package com.example.stickynotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.stickynotes.components.NoteDetailsModal
import com.example.stickynotes.db.NotesDB
import com.example.stickynotes.repository.NotesRepository
import com.example.stickynotes.screens.NoteList
import com.example.stickynotes.ui.theme.StickyNotesTheme
import com.example.stickynotes.viewmodel.NoteViewModel
import com.example.stickynotes.viewmodel.NoteViewModelFactory
import com.example.stickynotes.widgets.FloatButton

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = NotesDB.getInstance(applicationContext)
        val repository = NotesRepository(database.noteDao)
        val viewModelFactory = NoteViewModelFactory(repository)
        val noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        setContent {
            StickyNotesTheme {
                Scaffold(floatingActionButton = { DisplayNoteModal(viewModel = noteViewModel) }) {
                    val notes by noteViewModel.allNotes.observeAsState(emptyList());
                    if (notes.isEmpty()) {
                        ListEmptyContent()
                    } else {
                        NoteList(notes);
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayNoteModal(viewModel: NoteViewModel) {
    var showDialog by remember {
        mutableStateOf(false);
    }

    NoteDetailsModal(
        viewModel = viewModel,
        showDialog = showDialog,
        onDismiss = {
            showDialog = false
        }
    )

    FloatButton(onClick = {
        showDialog = true
    }, containerColor = Color.Blue, contentColor = Color.White)
}

@Composable
fun ListEmptyContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {
            Text(text = "Start creating notes to build your list")
            Spacer(modifier = Modifier.height(2.dp))
            Button(onClick = {}) {
                Text(text = "Create a note")
            }
        }
}
