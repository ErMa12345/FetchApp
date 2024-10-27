package com.example.fetchsubmission

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fetchsubmission.ui.theme.FetchSubmissionTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchSubmissionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Spacer(modifier = Modifier.height(32.dp))
                        Greeting(modifier = Modifier.padding(innerPadding))
                        GenerateTable()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier) {
    Text(
        text = "Eric Ma Fetch Submission",
    )
}

@Composable
fun GenerateTable() {
    var data by remember { mutableStateOf("Loading...") }
    var parsed by remember { mutableStateOf<List<GroupedItem>?>(null) }
    LaunchedEffect(Unit) {
        val pd = PullData()
        data = pd.getData()
        val parsedData = pd.parseData(data)
        parsed = parsedData
    }
    if (parsed == null) {
        BasicText(text = "Loading...")
    } else {
        LazyRow(modifier = Modifier.padding(16.dp)) {
            items(parsed!!) { group ->
                Column(modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(200.dp)) {
                    BasicText(text = "List ID: ${group.listId}", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))

                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(group.items) { item ->
                            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                BasicText(text = "Name: ${item.name}")
                                BasicText(text = "ID: ${item.id}")
                            }
                        }
                    }
                }
            }
        }
    }

}





