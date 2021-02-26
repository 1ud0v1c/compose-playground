package com.ludovic.vimont.composeplayground

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ludovic.vimont.composeplayground.model.Cat
import com.ludovic.vimont.composeplayground.network.CatWebService
import com.ludovic.vimont.composeplayground.ui.components.NetworkImage
import com.ludovic.vimont.composeplayground.ui.theme.ComposePlaygroundTheme

class MainActivity : AppCompatActivity() {
    private val defaultCat = CatWebService.defaultCat()
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.loadCat()
        setContent {
            val cat by mainViewModel.cat.observeAsState(defaultCat)
            ApplicationUI(cat)
        }
    }

    @Composable
    fun ApplicationUI(cat: Cat) {
        ComposePlaygroundTheme {
            Surface(color = MaterialTheme.colors.background) {
                Column(modifier = Modifier.fillMaxSize()) {
                    CreateTopBar()
                    HomeScreen(cat)
                }
            }
        }
    }

    @Composable
    fun CreateTopBar() {
        val context = LocalContext.current

        TopAppBar(title = {
            Row(modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Composable Playground",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Button(onClick = {
                     mainViewModel.loadCat()
                },
                    modifier = Modifier.align(Alignment.CenterVertically)) {
                    Image(
                        painter = painterResource(R.drawable.ic_refresh),
                        contentDescription = null,
                    )
                }
                Button(onClick = {
                        Toast.makeText(context, "Go to Settings screen", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)) {
                    Image(
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = null,
                    )
                }
            }
        })
    }

    @Composable
    fun HomeScreen(cat: Cat) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Hello Cat!",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp
                ))
            Spacer(Modifier.height(8.dp))
            NetworkImage(cat.image)
            Spacer(Modifier.height(8.dp))
            Column {
                for (fact in cat.facts) {
                    Text(text = fact.text,
                         style = TextStyle(
                            color = Color.Gray,
                            fontSize = 13.sp
                        ))
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ApplicationUI(CatWebService.defaultCat())
    }
}