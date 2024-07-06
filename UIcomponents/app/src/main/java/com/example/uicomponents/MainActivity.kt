package com.example.uicomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uicomponents.ui.theme.UIcomponentsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIcomponentsTheme {
                AlertDialogExample(
                    onDismissRequest = {},
                    onConfirmation = {},
                    dialogTitle = "Alert Dialog",
                    dialogText = "This is an alert dialog example using Jetpack Compose.",
                    icon = Icons.Default.Info
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Alert Icon")
        },
        title = {
            Text(text = dialogTitle, fontSize = 26.sp)
        },
        text = {
            Text(text = dialogText, style = MaterialTheme.typography.bodyLarge)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text("OK", style = MaterialTheme.typography.bodyMedium)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text("Cancel", style = MaterialTheme.typography.bodyMedium)
            }
        }
    )
}
