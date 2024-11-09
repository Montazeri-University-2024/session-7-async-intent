package com.sy.coroutineandintent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.sy.coroutineandintent.ui.theme.CoroutineAndIntentTheme

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val extras = intent.extras

            CoroutineAndIntentTheme {
                val configs = remember {
                    mutableStateOf(extras?.getString("result"))
                }
                Column {
                    Text(text = configs.value ?: "")
                }
            }
        }
    }
}
