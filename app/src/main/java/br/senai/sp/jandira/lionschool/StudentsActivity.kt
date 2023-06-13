package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.topLine
import br.senai.sp.jandira.lionschool.model.Student
import br.senai.sp.jandira.lionschool.model.StudentsList
import br.senai.sp.jandira.lionschool.services.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.math.log

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dados = intent.getStringExtra("curso")

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

    Log.i("tag", "onCreate: ${sigla}")

    val context = LocalContext.current


    var listStudent by remember {
        mutableStateOf(listOf<Student>())
    }

    var listStudent2 by remember {
        mutableStateOf(listOf<Student>())
    }



    var nomeCurso by remember {
        mutableStateOf("")
    }

    var list:List<Student>




    val call = RetrofitFactory().getStudentsServices().getAlunosCurso(sigla)

    call.enqueue(object  : retrofit2.Callback<StudentsList>{

        override fun onResponse(call: Call<StudentsList>, response: Response<StudentsList>) {

            nomeCurso = response.body()!!.nomeCurso
            listStudent = response.body()!!.informacoes
            listStudent2 = response.body()!!.informacoes
        }

        override fun onFailure(call: Call<StudentsList>, t: Throwable) {
            Log.i("ds2t", "onFailure: ${t.message}")
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

            Text(text = nomeCurso, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(51,71,176))


            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp), horizontalArrangement = Arrangement.SpaceAround) {
                Button(
                    onClick = { listStudent2 = listStudent.filter { it.status == "Finalizado" }},
                    modifier = Modifier
                        .width(80.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(Color(51,71,176))

                ) {
                    Text(text = "Finalizado", fontSize = 7.sp, color = Color.White)
                }
                Button(onClick = { listStudent2 = listStudent.filter { it.status == "Cursando" }},
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(Color(51,71,176))) {
                    Text(text = "Cursando", fontSize = 8.sp, color = Color.White)
                    
                }
                Button(
                    onClick = { list = listStudent
                                 listStudent2 = list},
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(Color(51,71,176))){
                    Text(text = "Todos", color = Color.White, fontSize = 8.sp)
                    
                }

            }

            LazyColumn(modifier = Modifier.padding(top = 15.dp)){
                items(listStudent2){

                    Button(onClick = {
                        var openStudentsInfoActivity = Intent(context, InfoStudentsActivity::class.java)
                        openStudentsInfoActivity.putExtra("matricula", it.matricula)
                        context.startActivity(openStudentsInfoActivity)
                    },
                    colors = ButtonDefaults.buttonColors(changeColorByStatus(it.status)),
                    modifier = Modifier
                        .height(200.dp)
                        .width(296.dp)
                        .padding(bottom = 12.dp)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AsyncImage(model = it.foto, contentDescription = "foto do ${it.nome}")
                            Text(text = it.nome, color = Color.White, fontSize = 14.sp)
                        }

                    }
                }
            }
        }
    }
}

fun changeColorByStatus(status: String):Color{

   return if(status == "Finalizado"){
        Color(229,182,87)
    }else{
        Color(51,71,176)
    }

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    LionSchoolTheme {
//        StudentsScreen(sigla = "rds")
//    }
//}