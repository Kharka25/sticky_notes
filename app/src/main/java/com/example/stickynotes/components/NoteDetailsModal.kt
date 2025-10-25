package com.example.stickynotes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.stickynotes.db.Note
import com.example.stickynotes.viewmodel.NoteViewModel

data class NoteDetailsModalState(
    var description: String = "",
    var selectedColor: Color,
    var title: String = ""
)

@Composable
fun NoteDetailsModal(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    showDialog: Boolean,
    viewModel: NoteViewModel) {

    var description by remember {
        mutableStateOf("");
    }

    var selectedColor by remember {
        mutableStateOf(Color.Blue)
    }

    var title by remember{
        mutableStateOf("");
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(onClick = {
                    val note = Note(
                        id = 0,
                        color = selectedColor.toArgb(),
                        description = description,
                        title = title)
                    viewModel.createNewNote(note);
                }) { Text(text = "Save note")} },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text(text = "Cancel")
                }
            },
            title = { Text(text = "Create A Note") },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = {title = it},
                        label = { Text(text = "Note Title") })
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = {
                            Text(text = "Note Description")})
                    Spacer(modifier = Modifier.height(16.dp))}}
        )
    }
}
