package com.example.uicomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uicomponents.ui.theme.UIcomponentsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIcomponentsTheme {
                val sheetState = rememberModalBottomSheetState()
                val scope = rememberCoroutineScope()
                var showBottomSheet by remember { mutableStateOf(false) }
                Scaffold(
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            text = { Text("Show bottom sheet") },
                            icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                            onClick = {
                                showBottomSheet = true
                            }
                        )
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (showBottomSheet) {
                            ModalBottomSheet(
                                onDismissRequest = {
                                    showBottomSheet = false
                                },
                                sheetState = sheetState
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    verticalArrangement = Arrangement.spacedBy(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Bottom Sheet Content")
                                    Button(onClick = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            if (!sheetState.isVisible) {
                                                showBottomSheet = false
                                            }
                                        }
                                    }) {
                                        Text("Hide bottom sheet")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
