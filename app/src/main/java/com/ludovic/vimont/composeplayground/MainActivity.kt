package com.ludovic.vimont.composeplayground

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ludovic.vimont.composeplayground.model.RandomCat
import com.ludovic.vimont.composeplayground.network.CatWebService
import com.ludovic.vimont.composeplayground.ui.components.NetworkImage
import com.ludovic.vimont.composeplayground.ui.theme.ComposePlaygroundTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            val cat = CatWebService.getCat()
            println(cat)
        }

        setContent {
            ApplicationUI()
        }
    }
}

@Composable
fun ApplicationUI() {
    ComposePlaygroundTheme {
        Surface(color = MaterialTheme.colors.background) {
            HomeScreen("Android")
        }
    }
}

@Composable
fun HomeScreen(name: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hello $name!")
        Spacer(Modifier.height(16.dp))
        NetworkImage("https://picsum.photos/seed/picsum/200/300")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApplicationUI()
}