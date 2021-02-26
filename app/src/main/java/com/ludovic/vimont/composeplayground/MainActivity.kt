package com.ludovic.vimont.composeplayground

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.ludovic.vimont.composeplayground.model.CatFacts
import com.ludovic.vimont.composeplayground.network.CatWebService
import com.ludovic.vimont.composeplayground.ui.components.NetworkImage
import com.ludovic.vimont.composeplayground.ui.theme.ComposePlaygroundTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

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
            Column(modifier = Modifier.fillMaxSize()) {
                CreateTopBar()
                val image = "https://picsum.photos/seed/picsum/200/300"
                val facts = listOf(
                    CatFacts("Test", Date().time.toString()),
                    CatFacts("Lorem Ipsum", Date().time.toString()),
                    CatFacts("I love cats", Date().time.toString()),
                    CatFacts("Very much", Date().time.toString())
                )
                HomeScreen(Cat(image, facts))
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
    ApplicationUI()
}