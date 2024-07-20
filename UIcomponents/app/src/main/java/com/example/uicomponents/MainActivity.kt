package com.example.uicomponents

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uicomponents.ui.theme.UIcomponentsTheme

class MainActivity : ComponentActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        setContent {
            UIcomponentsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    ScreenOne()
                }
            }
        }
    }

    @Composable
    fun ScreenOne() {
        var userName by remember { mutableStateOf("") }
        var savedUserName by remember { mutableStateOf("") }
        var showToast by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            savedUserName = getUserName() ?: ""
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color(0xFFEFEFEF))
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "User Information",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Enter Name") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        saveUserName(userName)
                        savedUserName = userName
                        showToast = true
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Filled.Check, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Save Name")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        userName = ""
                        savedUserName = ""
                    },
                    modifier = Modifier.weight(1f),
                ) {
                    Icon(Icons.Filled.Clear, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Clear")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Saved Name: $savedUserName",
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        if (showToast) {
            Toast.makeText(this@MainActivity, "Name saved successfully!", Toast.LENGTH_SHORT).show()
            showToast = false
        }
    }
    fun saveUserName(userName: String) {
        val editor = sharedPreferences.edit()
        editor.putString("user_name", userName)
        editor.apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString("user_name", null)
    }
}