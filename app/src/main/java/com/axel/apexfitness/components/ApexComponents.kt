package com.axel.apexfitness.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.axel.apexfitness.ui.theme.AppInputBackground
import com.axel.apexfitness.ui.theme.AppTheme

// INPUT REUTILIZABLE
@Composable
fun ApexTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = AppTheme.spacing.extraSmall)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            leadingIcon = leadingIcon?.let {
                { Icon(imageVector = it, contentDescription = null, tint = MaterialTheme.colorScheme.primary) }
            },
            trailingIcon = trailingIcon,
            shape = RoundedCornerShape(AppTheme.spacing.medium),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = AppInputBackground,
                unfocusedContainerColor = AppInputBackground,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// CARD REUTILIZABLE
@Composable
fun ApexStatCard(
    title: String,
    value: String,
    subtitle: String? = null,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(AppTheme.sizes.cardCornerRadius),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    ) {
        Row(
            modifier = Modifier
                .padding(AppTheme.spacing.regular)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Box(
                    modifier = Modifier
                        .size(AppTheme.sizes.iconContainerMedium)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.width(AppTheme.spacing.medium))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

// MODAL DIALOG REUTILIZABLE
@Composable
fun ApexModalDialog(
    title: String,
    onDismissRequest: () -> Unit,
    confirmButtonText: String = "Guardar",
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(AppTheme.sizes.cardCornerRadius),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.spacing.medium)
        ) {
            Column(
                modifier = Modifier.padding(AppTheme.spacing.regular)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    IconButton(onClick = onDismissRequest) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

                content()

                Spacer(modifier = Modifier.height(AppTheme.spacing.large))

                Button(
                    onClick = onConfirm,
                    shape = RoundedCornerShape(AppTheme.sizes.buttonCornerRadius),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(AppTheme.sizes.buttonHeight)
                ) {
                    Text(
                        text = confirmButtonText,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
