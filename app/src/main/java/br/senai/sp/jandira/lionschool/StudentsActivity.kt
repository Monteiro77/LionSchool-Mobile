package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionschool.components.topLine
import br.senai.sp.jandira.lionschool.model.Student
import br.senai.sp.jandira.lionschool.model.StudentsList
import br.senai.sp.jandira.lionschool.services.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dados = intent.getStringExtra("sigla")
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                    StudentsScreen(dados.toString())
            }
        }
    }
}



@Composable
fun StudentsScreen(sigla: String) {

    val context = LocalContext.current

    var searchState by remember {
        mutableStateOf("")
    }

    var listStudent by remember {
        mutableStateOf(listOf<Student>())
    }

    var nomeCurso by remember {
        mutableStateOf("")
    }




    val call = RetrofitFactory().getStudentsServices().getAlunos()

    call.enqueue(object  : retrofit2.Callback<StudentsList>{

        override fun onResponse(call: Call<StudentsList>, response: Response<StudentsList>) {
            listStudent = response.body()!!.informacoes
        }

        override fun onFailure(call: Call<StudentsList>, t: Throwable) {

        }

    })

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "",
                modifier = Modifier.size(80.dp)
            )
            topLine()

            Text(text = "Redes de Computadores")

            OutlinedTextField(
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
                    .padding(end = 8.dp),
                value = searchState,
                onValueChange = {searchState = it},
                colors =
                TextFieldDefaults
                    .outlinedTextFieldColors(
                        backgroundColor = Color(218,218,218),
                        focusedBorderColor = Color(51,71,176),
                        unfocusedBorderColor = Color(51,71,176,0),
                        focusedLabelColor = Color(51,71,176),
                        cursorColor = Color(51,71,176)
                    ),
                shape = RoundedCornerShape(30.dp),
                label = { Text(text = stringResource(id = R.string.find_your_course)) },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = null, tint = Color(126,123,123))
                },

            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(70.dp)
                        .height(20.dp),

                ) {
                    
                }
                Button(onClick = { /*TODO*/ }) {
                    
                }
                Button(onClick = { /*TODO*/ }) {
                    
                }
                
            }
            




        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    LionSchoolTheme {
//        StudentsScreen(sigla = "rds")
//    }
//}