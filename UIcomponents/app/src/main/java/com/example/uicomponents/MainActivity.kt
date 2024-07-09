package com.example.uicomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.uicomponents.ui.theme.UIcomponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIcomponentsTheme {
                LazyColumnSample()
            }
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "screen1") {
            composable(route = "screen1") { Screen1(navController) }
            composable(route = "screen2") { Screen2(navController) }
            composable(route = "screen3") { Screen3(navController) }
        }
    }

    @Composable
    fun Screen1(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to Screen 1", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Image(painter = painterResource(id = R.drawable.android), contentDescription = "Sample Image")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("screen2") }) {
                Text(text = "Go to Screen 2")
            }
        }
    }

    @Composable
    fun Screen2(navController: NavController) {
        var text by remember { mutableStateOf(" ") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "This is Screen 2", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate("screen3") }) {
                Text(text = "Go to Screen 3")
            }
        }
    }

    @Composable
    fun Screen3(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "This is Screen 3", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "I am inside a Card")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Button(onClick = { navController.navigate("screen1") }) {
                Text(text = "Go Back to Screen 1")
            }
        }
    }

    @Composable
    fun LazyColumnSample() {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            items(100) { index ->
                Text(text = "$index", modifier = Modifier.padding(8.dp))
                Divider(color = Color.Gray)
            }
        }
    }
    @Composable
    fun LazyRowSample() {
        LazyRow(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            items(50) { index ->
                Text(text = "$index", modifier = Modifier.padding(8.dp))
                Divider(color = Color.Gray)
            }
        }
    }
    @Composable
    fun GridSample() {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            items(50) { index ->
                Card(
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text(text = "$index", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
