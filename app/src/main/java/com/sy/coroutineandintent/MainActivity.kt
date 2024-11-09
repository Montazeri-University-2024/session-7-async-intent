package com.sy.coroutineandintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val result = remember {
                mutableStateOf("")
            }
            val loading = remember {
                mutableStateOf(false)
            }
            val coroutineScope = rememberCoroutineScope()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch(Dispatchers.Default) {  // Use Default dispatcher for computation
                            loading.value = true
                            var sum = 0.0
                            for (i in 0..1_000_000) {
                                sum += i
                            }
                            loading.value = false
                            result.value = sum.toString()
                            goToResultActivity(this@MainActivity, sum.toString())
                        }
                    },
                    enabled = !loading.value,
                    colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = Color.Gray.copy(alpha = 0.6f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (loading.value) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White
                        )
                    } else {
                        Text("Click Me")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (result.value.isNotEmpty()) {
                    Text(text = "Result: ${result.value}", fontSize = 20.sp)
                }
            }
        }
    }
}

fun goToResultActivity(context: Context, result: String) {
    val intent = Intent(context, ResultActivity::class.java)
    val bundle = Bundle()
    bundle.putString("result", result)
    intent.putExtras(bundle)
    context.startActivity(intent)
}

