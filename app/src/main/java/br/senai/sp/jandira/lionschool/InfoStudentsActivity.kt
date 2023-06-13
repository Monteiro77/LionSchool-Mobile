package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.cardLineStudent
import br.senai.sp.jandira.lionschool.components.topLine
import br.senai.sp.jandira.lionschool.model.Discipline
import br.senai.sp.jandira.lionschool.model.StudentGrade
import br.senai.sp.jandira.lionschool.services.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Response

class InfoStudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dados = intent.getStringExtra("matricula")
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                StudentsInfoScreen(matricula = dados.toString())
            }
        }
    }
}

@Composable
fun StudentsInfoScreen(matricula: String) {

    var nomeAluno by remember {
        mutableStateOf("")
    }

    var fotoAluno by remember {
        mutableStateOf("")
    }

    var matriculaAluno by remember {
        mutableStateOf("")
    }

    var nomeCurso by remember {
        mutableStateOf("")
    }

    var listDisicplinas by remember {
        mutableStateOf(listOf<Discipline>())
    }



    val call = RetrofitFactory().getStudentsServices().getAlunoByMatrciula(matricula)

    call.enqueue(object : retrofit2.Callback<StudentGrade>{

        override fun onResponse(call: Call<StudentGrade>, response: Response<StudentGrade>) {

            nomeAluno = response.body()!!.nome
            fotoAluno = response.body()!!.foto
            matriculaAluno = response.body()!!.matricula
            listDisicplinas = response.body()!!.disciplinas
            nomeCurso = response.body()!!.nomeCurso


        }

        override fun onFailure(call: Call<StudentGrade>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })


    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "",
                modifier = Modifier.size(80.dp)
            )
            topLine()

            Text(
                text = nomeCurso,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(51,71,176),
                modifier = Modifier.padding(top = 5.dp)
            )

            Card(
                modifier = Modifier
                    .height(554.dp)
                    .width(332.dp)
                    .padding(top = 12.dp),
                backgroundColor = Color(51,71,176)
            ) {
                Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(model = fotoAluno, contentDescription = "Foto do ${nomeAluno}", modifier = Modifier.padding(top = 12.dp))
                    Text(text = nomeAluno.uppercase(), color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = " Matricula: ${matriculaAluno}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(20.dp))
                    cardLineStudent()
                    Spacer(modifier = Modifier.height(20.dp))

                    Card(
                        modifier = Modifier
                            .height(240.dp)
                            .width(297.dp),
                        backgroundColor = Color.White
                    ) {

                        LazyColumn(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center){
                            items(listDisicplinas){
                                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = it.sigla, fontWeight = FontWeight.Bold, color = Color(51,71,176), modifier = Modifier.shadow(24.dp))

                                    Text(text = it.media)

                                    Box(
                                        modifier = Modifier
                                            .width(135.dp)
                                            .height(15.dp)
                                            .background(
                                                Color(217, 217, 217)
                                            ),
                                    ) {
                                        Box(modifier = Modifier.width(it.media.toDouble().dp).background(changeColorByMedia(it.media.toDouble())
                                        ).height(15.dp))
                                    }
                                }
                            }
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            topLine()

            Row() {
                Image(painter = painterResource(id = R.drawable.icons_social), contentDescription = "", modifier = Modifier.size(190.dp))
            }





        }
    }
}

fun changeColorByMedia(media : Double):Color{

    return if(media <= 100 && media >= 80){
        Color(51,71,176)
    }else if (media < 80 && media >= 50){
        Color(229,182,87)
    }else{
        Color(204,81,81)
    }

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    LionSchoolTheme {
//    }
//}