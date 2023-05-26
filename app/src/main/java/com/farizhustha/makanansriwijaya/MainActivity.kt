package com.farizhustha.makanansriwijaya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.farizhustha.makanansriwijaya.ui.theme.MakananSriwijayaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakananSriwijayaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.error
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text("Makanan Sriwijaya")
                                },
                                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Red)
                            )

                        },
                        content = { innerPadding ->
                            Column(
                                modifier = Modifier.fillMaxSize().padding(innerPadding),

                            ) {
                                Greeting(name = "fariz")
                            }

                        }
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MakananSriwijayaTheme {
        Greeting("Android")
    }
}