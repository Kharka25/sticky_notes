package com.example.stickynotes.widgets

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.stickynotes.R

@Composable
fun FloatButton(onClick: () -> Unit,
                containerColor: Color = Color.DarkGray,
                contentColor: Color = Color.Black,
                painter: Painter = painterResource(R.drawable.outline_add_24)) {
    FloatingActionButton(
        onClick,
        containerColor = containerColor,
        contentColor = contentColor) {
        Icon(painter, contentDescription = "Create Note button")
    }
}
