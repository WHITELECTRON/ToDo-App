package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.todoapp.data.room_database.Taskitem
import com.example.todoapp.ui.theme.darkGrey
import com.example.todoapp.ui.theme.grey
import com.example.todoapp.viewmodel.TaskViewModel

//@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(viewModel: TaskViewModel) {


 val tasks by viewModel.allTasks.collectAsState()
    var taskToEdit by remember { mutableStateOf<Taskitem?>(null) }
    var showEditorDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { taskToEdit=null
                    showEditorDialog=true
                          },
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
            if(tasks.isEmpty()){
                Box(
                    modifier=Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text="No Tasks",
                        color= grey
                    )
                }

            }else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding= PaddingValues(bottom = 80.dp)

                ) {
                    items(
                        items=tasks,
                        key={it.id}
                    ){ task ->
                        ToDoItem(
                            item=task,
                            onEditClick = {
                                taskToEdit=task
                                showEditorDialog=true
                            },
                            onDeleteClick = {viewModel.deleteTask(task)},
                            onCheckChange={checked ->viewModel.updateTask(task.copy(isDone=checked))}
                        ) 


                    }
                }
            }
        }
    }
    if(showEditorDialog)
    {
        TaskEditorDialog(
            task=taskToEdit,
            onSave={newName->
             if(taskToEdit==null){
                 viewModel.addTask(Taskitem(taskName=newName, isDone = false))
             } else {
                 taskToEdit?.let { currentTask ->
                     viewModel.updateTask(currentTask.copy(taskName = newName))
                 }
             }
                showEditorDialog=false
                taskToEdit=null
            },
            onCancel={
                showEditorDialog=false
                taskToEdit=null
            }

        )
    }
}