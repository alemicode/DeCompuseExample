package com.plcoding.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plcoding.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel: MainViewModel by viewModels()

        setContent {
            MyApplicationTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val action = remember {
                        MainAction(
                            changeName = mainViewModel::changeName,
                            getData = mainViewModel::getData,
                            plus = mainViewModel::plus
                        )
                    }


                    val state by remember(mainViewModel) {
                        action.getData()
                        mainViewModel.state
                    }.collectAsState()

                    when (state) {
                        is MainState.Init -> {
                            with(state as MainState.Init) {
                                MainScreen(
                                    action,
                                    data = data,
                                    name = name
                                )
                            }
                        }
                    }

                }
            }
        }
    }

}

@Composable
private fun mnageLazyColumn(data: State<List<MainModel>>, action: MainAction) {
    Log.d("TAG", "BottumClickable: lazyyyyyyyy")

    LazyColumn(
    ) {
        itemsIndexed(data.value) { index, item ->
            ItemInEachRow(model = item)
            action.plus()
        }
    }
}

@Composable
private fun BottumClickable(changeName: (name: String) -> Unit, name: String) {

    Log.d("TAG", "BottumClickable: btnnnnnnnn")
    Button(
        onClick = { changeName("Soheyl") }, modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color.Blue)
    ) {
        Text(text = name, Modifier.background(Color.Transparent))
    }
}

@Composable
private fun MainScreen(action: MainAction, data: State<List<MainModel>>, name: String) {

    Column() {
        BottumClickable(action.changeName, name)

        mnageLazyColumn(data, action)
    }
}

@Composable
fun ItemInEachRow(
    model: MainModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(end = 20.dp, start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        val context = LocalContext.current
        Image(
            painter = painterResource(id = model.profile),
            contentDescription = context.getString(R.string.app_name)
        )
        Text(text = model.name)
    }
}


