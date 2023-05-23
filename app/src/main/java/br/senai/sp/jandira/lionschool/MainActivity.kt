package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.mediumLine
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                ) {
                    mainScreen()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun mainScreen() {
    LionSchoolTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.studant)
                        ,contentDescription = null
                        , modifier = Modifier
                            .size(400.dp)

                    )
                    mediumLine()
                }


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.get_started_with),
                        color = Color(163,153,153)
                    )
                    Text(
                        text = stringResource(id = R.string.name_school),
                        color = Color(51,71,176),
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(width = 296.dp, height = 56.dp),
                    colors = ButtonDefaults.buttonColors(Color(51,71,176))

                ) {
                    Text(
                        text = stringResource(id = R.string.get_started),
                        color = Color.White,
                        fontSize = 24.sp
                    )
                    
                }
            }
            
        }
    }
}