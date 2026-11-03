package com.axel.apexfitness.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.axel.apexfitness.components.ApexModalDialog
import com.axel.apexfitness.components.ApexStatCard
import com.axel.apexfitness.components.ApexTextField
import com.axel.apexfitness.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevoMiembroScreen(onRegistroExitoso: () -> Unit = {}) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var planSelected by remember { mutableStateOf("Plan Black VIP") }

    var showSuccessModal by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Miembro", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = AppTheme.spacing.regular)
                .fillMaxSize()
        ) {
            ApexTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = "Nombres",
                leadingIcon = Icons.Outlined.Person
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            ApexTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = "Apellidos",
                leadingIcon = Icons.Outlined.Person
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            ApexTextField(
                value = email,
                onValueChange = { email = it },
                label = "Correo Electrónico",
                leadingIcon = Icons.Outlined.Email
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            ApexTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = "Teléfono de Contacto",
                leadingIcon = Icons.Outlined.Phone
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            ApexTextField(
                value = planSelected,
                onValueChange = { planSelected = it },
                label = "Plan Elegido",
                leadingIcon = Icons.Outlined.Badge
            )

            Spacer(modifier = Modifier.height(AppTheme.spacing.large))

            Button(
                onClick = { showSuccessModal = true },
                enabled = nombre.isNotBlank() && email.isNotBlank(),
                shape = RoundedCornerShape(AppTheme.sizes.buttonCornerRadius),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppTheme.sizes.buttonHeight)
            ) {
                Text(
                    text = "Confirmar Registro",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        if (showSuccessModal) {
            ApexModalDialog(
                title = "¡Miembro Registrado!",
                confirmButtonText = "Aceptar",
                onDismissRequest = { showSuccessModal = false },
                onConfirm = {
                    showSuccessModal = false
                    onRegistroExitoso()
                }
            ) {
                Text(
                    text = "Se ha dado de alta a $nombre $apellido en la plataforma correctamente.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
