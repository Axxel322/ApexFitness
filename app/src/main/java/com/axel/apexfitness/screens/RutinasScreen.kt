package com.axel.apexfitness.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.axel.apexfitness.components.ApexModalDialog
import com.axel.apexfitness.components.ApexStatCard
import com.axel.apexfitness.components.ApexTextField
import com.axel.apexfitness.ui.theme.AppTheme

data class Routine(val id: Int, val name: String, val duration: String, val level: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutinasScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var newRoutineName by remember { mutableStateOf("") }
    var newRoutineDuration by remember { mutableStateOf("") }

    val routines = remember {
        mutableStateListOf(
            Routine(1, "Hipertrofia Pierna & Glúteo", "60 min", "Avanzado"),
            Routine(2, "Torso Empuje (Pecho/Hombro/Tríceps)", "50 min", "Intermedio"),
            Routine(3, "Cardio HIIT & Core", "30 min", "Principiante")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rutinas de Entrenamiento", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir Rutina", tint = Color.Black)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = AppTheme.spacing.regular),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
        ) {
            items(routines) { routine ->
                ApexStatCard(
                    title = routine.level.uppercase(),
                    value = routine.name,
                    subtitle = "Duración: ${routine.duration}",
                    icon = Icons.Outlined.FitnessCenter
                )
            }
        }

        if (showDialog) {
            ApexModalDialog(
                title = "Nueva Rutina",
                onDismissRequest = { showDialog = false },
                onConfirm = {
                    if (newRoutineName.isNotBlank()) {
                        routines.add(Routine(routines.size + 1, newRoutineName, newRoutineDuration.ifEmpty { "45 min" }, "Personalizado"))
                        newRoutineName = ""
                        newRoutineDuration = ""
                        showDialog = false
                    }
                }
            ) {
                ApexTextField(
                    value = newRoutineName,
                    onValueChange = { newRoutineName = it },
                    label = "Nombre de la Rutina",
                    leadingIcon = Icons.Outlined.FitnessCenter
                )
                Spacer(modifier = Modifier.height(AppTheme.spacing.medium))
                ApexTextField(
                    value = newRoutineDuration,
                    onValueChange = { newRoutineDuration = it },
                    label = "Duración estimada (ej. 45 min)",
                    leadingIcon = Icons.Outlined.Timer
                )
            }
        }
    }
}
