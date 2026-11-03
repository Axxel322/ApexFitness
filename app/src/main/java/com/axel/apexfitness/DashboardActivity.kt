package com.axel.apexfitness

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsWalk
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.axel.apexfitness.screens.MetricasScreen
import com.axel.apexfitness.screens.NuevoMiembroScreen
import com.axel.apexfitness.screens.RutinasScreen
import com.axel.apexfitness.ui.theme.ApexFitnessTheme
import com.axel.apexfitness.ui.theme.AppTheme

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApexFitnessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Estado para cambiar de vista activamente desde el menú
                    var currentScreen by remember { mutableStateOf("dashboard") }

                    when (currentScreen) {
                        "rutinas" -> RutinasScreen()
                        "metricas" -> MetricasScreen()
                        "nuevo_miembro" -> NuevoMiembroScreen(onRegistroExitoso = { currentScreen = "dashboard" })
                        else -> DashboardScreen(
                            onVerMiembrosClick = {
                                startActivity(Intent(this@DashboardActivity, MiembrosActivity::class.java))
                            },
                            onNavigateTo = { screenName -> currentScreen = screenName }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onVerMiembrosClick: () -> Unit,
    onNavigateTo: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = stringResource(R.string.dashboard_greeting),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.outline
                        )
                        Text(
                            text = "Axel Espinoza",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = AppTheme.spacing.regular)
                            .size(AppTheme.sizes.iconContainerMedium)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = AppTheme.spacing.regular)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.dashboard_activity_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.regular))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
            ) {
                StatCard(
                    icon = Icons.Outlined.LocalFireDepartment,
                    value = "320",
                    label = stringResource(R.string.dashboard_calories),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    icon = Icons.Outlined.DirectionsWalk,
                    value = "6,240",
                    label = stringResource(R.string.dashboard_steps),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    icon = Icons.Outlined.Timer,
                    value = "48m",
                    label = stringResource(R.string.dashboard_active),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(AppTheme.spacing.large))

            Text(
                text = "Navegación de Vistas",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            // Botón Vista 1: Rutinas
            Button(
                onClick = { onNavigateTo("rutinas") },
                shape = RoundedCornerShape(AppTheme.sizes.buttonCornerRadius),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.fillMaxWidth().height(AppTheme.sizes.buttonHeight)
            ) {
                Text(text = "Ver Rutinas de Entrenamiento", color = Color.Black, style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(AppTheme.spacing.small))

            // Botón Vista 2: Métricas
            Button(
                onClick = { onNavigateTo("metricas") },
                shape = RoundedCornerShape(AppTheme.sizes.buttonCornerRadius),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth().height(AppTheme.sizes.buttonHeight)
            ) {
                Text(text = "Ver Métricas Corporal", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(AppTheme.spacing.small))

            // Botón Vista 3: Registrar Miembro
            Button(
                onClick = { onNavigateTo("nuevo_miembro") },
                shape = RoundedCornerShape(AppTheme.sizes.buttonCornerRadius),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth().height(AppTheme.sizes.buttonHeight)
            ) {
                Text(text = "Registrar Nuevo Miembro", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(AppTheme.spacing.small))

            // Botón API Backend Miembros
            OutlinedButton(
                onClick = onVerMiembrosClick,
                shape = RoundedCornerShape(AppTheme.sizes.buttonCornerRadius),
                modifier = Modifier.fillMaxWidth().height(AppTheme.sizes.buttonHeight)
            ) {
                Text(text = stringResource(R.string.dashboard_view_members), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
fun StatCard(
    icon: ImageVector,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.sizes.cardCornerRadius))
            .background(MaterialTheme.colorScheme.surface)
            .padding(AppTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(AppTheme.spacing.extraSmall))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outline
        )
    }
}