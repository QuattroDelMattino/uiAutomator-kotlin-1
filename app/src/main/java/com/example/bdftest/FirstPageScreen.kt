package com.example.bdftest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bdftest.ui.theme.BDFTestTheme

@Composable
fun FirstPage(onNavigateToSecondPage: (String) -> Unit) {
    FirstScreenContent(name = "Android", onButtonClick = onNavigateToSecondPage)
}

@Composable
private fun FirstScreenContent(
    name: String,
    modifier: Modifier = Modifier,
    onButtonClick: (String) -> Unit
) {

    var text by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("UserInputField")
                .padding(all = 8.dp),
            value = text,
            onValueChange = {
                text = it
                errorMsg = ""
            },
            label = { Text("Label") }

        )
        Text(
            text = errorMsg,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .testTag("ErrorMsg"),
            color = Color.Red
        )
        TextButton(
            modifier = Modifier.testTag("ContinueButton"),
            onClick = {
                if (text.isBlank()) {
                    errorMsg = "Numele userului trebuie inserat!"
                } else {
                    if (text.matches("^[a-zA-Z]+\$".toRegex())) {
                        if (text.equals("DuckDodgers")) {
                            onButtonClick(text)
                        } else {
                            errorMsg = "User necunoscut"
                        }
                    } else {
                        errorMsg = "Acest input-field nu suporta caractere speciale"
                    }
                }
            }) {
            Text(text = "Continue", modifier = Modifier.testTag("ContinueButtonText"))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BDFTestTheme {
        FirstScreenContent(name = "Android", onButtonClick = {})
    }
}