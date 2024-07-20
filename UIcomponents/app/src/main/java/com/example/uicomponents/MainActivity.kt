package com.example.uicomponents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uicomponents.ui.theme.UIcomponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIcomponentsTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFFEAEAEA)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Discover UI Design Principles",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 18.dp)
        )

        val clickableText = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, textDecoration = TextDecoration.None)) {
                append("Tap ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, textDecoration = TextDecoration.Underline)) {
                append("here")
            }
            append(" to access detailed design guidelines.")
        }

        Text(
            text = clickableText,
            fontSize = 18.sp,
            modifier = Modifier
                .clickable {
                    launchBrowser(context, "https://developer.android.com/design/ui")
                }
                .padding(14.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
    }
}
fun launchBrowser(context: android.content.Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
