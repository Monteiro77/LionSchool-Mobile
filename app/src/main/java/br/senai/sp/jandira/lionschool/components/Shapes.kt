package br.senai.sp.jandira.lionschool.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun mediumLine() {
    Card(modifier = Modifier.size(width = 333.dp, height = 10.dp),
        backgroundColor = Color(229,187,87)
    ) {

    }
}

@Composable
fun topLine() {
    Card(modifier = Modifier.size(width = 332.dp, height = 5.dp),
        backgroundColor = Color(229,187,87)
    ) {
        
    }
}

@Composable
fun cardLineStudent() {
    Card(modifier = Modifier.size(width = 200.dp, height = 5.dp),
        backgroundColor = Color(229,187,87)
    ) {

    }
}

@Composable
fun cardLine() {
    Card(
        modifier = Modifier.size(width = 332.dp, height = 3.dp),
        backgroundColor = Color(255,255,255)
    ) {
        
    }
}



@Preview
@Composable
fun LinePreview(){
    mediumLine()
    topLine()
    cardLine()
}

