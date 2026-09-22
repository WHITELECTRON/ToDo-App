package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import com.example.todoapp.ui.theme.darkGrey
import com.example.todoapp.viewmodel.TaskViewModel

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToDoListScreen(viewModel: TaskViewModel) {


 val tasks by viewModel.allTasks.collectAsState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { },
                shape = RoundedCornerShape(size = 20.dp),
                containerColor = darkGrey,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task"
                )

                Text(
                    text = "New Task",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "My Tasks",
                modifier = Modifier.padding(top = 32.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
             color=darkGrey
            )
         Text(
          text="${tasks.filter{!it.isDone}.size}remaining today",
          color=Color.Gray
         )
        }
    }
}