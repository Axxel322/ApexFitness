package com.axel.apexfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.axel.apexfitness.model.Miembro
import com.axel.apexfitness.network.RetrofitClient
import com.axel.apexfitness.ui.theme.AppTheme
import com.axel.apexfitness.ui.theme.ApexFitnessTheme
import kotlinx.coroutines.launch

class MiembrosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApexFitnessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MiembrosScreen(onBackClick = { finish() })
                }
            }
        }
    }
}

sealed interface MiembrosUiState {
    data object Loading : MiembrosUiState
    data class Success(val miembros: List<Miembro>) : MiembrosUiState
    data class Error(val mensaje: String) : MiembrosUiState
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiembrosScreen(onBackClick: () -> Unit) {
    var uiState by remember { mutableStateOf<MiembrosUiState>(MiembrosUiState.Loading) }
    val scope = rememberCoroutineScope()

    fun cargarMiembros() {
        uiState = MiembrosUiState.Loading
        scope.launch {
            try {
                val response = RetrofitClient.apiService.getMiembros()
                uiState = if (response.success) {
                    MiembrosUiState.Success(response.data)
                } else {
                    MiembrosUiState.Error("El servidor respondió con un error")
                }
            } catch (e: Exception) {
                uiState = MiembrosUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    LaunchedEffect(Unit) { cargarMiembros() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.miembros_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (val state = uiState) {
                is MiembrosUiState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(AppTheme.spacing.regular))
                        Text(
                            text = stringResource(R.string.miembros_loading),
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }

                is MiembrosUiState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(AppTheme.spacing.large),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.miembros_error),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(AppTheme.spacing.regular))
                        Button(
                            onClick = { cargarMiembros() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(stringResource(R.string.miembros_retry), color = Color.Black)
                        }
                    }
                }

                is MiembrosUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(AppTheme.spacing.regular),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
                    ) {
                        items(state.miembros) { miembro ->
                            MiembroCard(miembro)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MiembroCard(miembro: Miembro) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.sizes.cardCornerRadius))
            .background(MaterialTheme.colorScheme.surface)
            .padding(AppTheme.spacing.regular)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${miembro.nombre} ${miembro.apellido}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            EstadoBadge(estado = miembro.estado)
        }

        Spacer(modifier = Modifier.height(AppTheme.spacing.small))

        InfoRow(label = "Plan", value = miembro.plan)
        InfoRow(label = "Entrenador", value = miembro.entrenador)
        InfoRow(label = "Teléfono", value = miembro.telefono)
        InfoRow(label = "Email", value = miembro.email)
        InfoRow(label = "Inscripción", value = miembro.fechaInscripcion)
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun EstadoBadge(estado: String) {
    val isActivo = estado.equals("Activo", ignoreCase = true)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(
                if (isActivo) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = estado,
            style = MaterialTheme.typography.labelMedium,
            color = if (isActivo) Color.Black else MaterialTheme.colorScheme.onBackground
        )
    }
}