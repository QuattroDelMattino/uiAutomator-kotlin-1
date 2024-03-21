package com.example.bdftest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun SecondPage(name: String) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SecondPageContent(name)
    }
}

private @Composable
fun SecondPageContent(name: String) {

    Column {
        Text(text = "Welcome back $name", modifier = Modifier.testTag("LoggedInUserText"))
        Text(
            text = "You have successfully logged in",
            modifier = Modifier
                .padding(top = 8.dp)
                .testTag("SuccessMsg"),
        )
    }
}