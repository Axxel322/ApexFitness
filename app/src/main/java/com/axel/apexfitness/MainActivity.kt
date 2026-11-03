package com.axel.apexfitness

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.axel.apexfitness.ui.theme.AppTheme
import com.axel.apexfitness.ui.theme.ApexFitnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApexFitnessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(
                        onLoginSuccess = {
                            startActivity(Intent(this@MainActivity, DashboardActivity::class.java))
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.spacing.large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "APEX",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "FITNESS",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
            letterSpacing = 4.sp
        )
        Spacer(modifier = Modifier.height(AppTheme.spacing.extraLarge))

        Text(
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(R.string.login_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(AppTheme.spacing.large))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.login_email_label)) },
            singleLine = true,
            shape = RoundedCornerShape(AppTheme.spacing.medium),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(AppTheme.spacing.regular))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.login_password_label)) },
            singleLine = true,
            shape = RoundedCornerShape(AppTheme.spacing.medium),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(AppTheme.spacing.large))

        Button(
            onClick = onLoginSuccess,
            shape = RoundedCornerShape(AppTheme.sizes.buttonCornerRadius),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.sizes.buttonHeight)
        ) {
            Text(
                text = stringResource(R.string.login_button),
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}