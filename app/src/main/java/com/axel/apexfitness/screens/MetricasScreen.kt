package com.axel.apexfitness.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessibilityNew
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.Percent
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
fun MetricasScreen() {
    var peso by remember { mutableStateOf("74.5") }
    var grasa by remember { mutableStateOf("15.2") }
    var masaMuscular by remember { mutableStateOf("38.1") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seguimiento Corporal", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = AppTheme.spacing.regular)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Último Registro",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
            ) {
                ApexStatCard(
                    title = "PESO ACTUAL",
                    value = "$peso kg",
                    icon = Icons.Outlined.MonitorWeight,
                    modifier = Modifier.weight(1f)
                )
                ApexStatCard(
                    title = "% GRASA",
                    value = "$grasa%",
                    icon = Icons.Outlined.Percent,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(AppTheme.spacing.large))
            Text(
                text = "Actualizar Métricas",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            ApexTextField(
                value = peso,
                onValueChange = { peso = it },
                label = "Peso Corporal (kg)",
                leadingIcon = Icons.Outlined.MonitorWeight
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            ApexTextField(
                value = grasa,
                onValueChange = { grasa = it },
                label = "Porcentaje de Grasa (%)",
                leadingIcon = Icons.Outlined.Percent
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            ApexTextField(
                value = masaMuscular,
                onValueChange = { masaMuscular = it },
                label = "Masa Muscular (kg)",
                leadingIcon = Icons.Outlined.AccessibilityNew
            )

            Spacer(modifier = Modifier.height(AppTheme.spacing.large))

            Button(
                onClick = { /* Lógica para guardar */ },
                shape = RoundedCornerShape(AppTheme.sizes.buttonCornerRadius),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppTheme.sizes.buttonHeight)
            ) {
                Text(
                    text = "Guardar Registro",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
