package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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

    var listStudent by remember {
        mutableStateOf(listOf<Student>())
    }

    var name by remember {
        mutableStateOf("")
    }




    val call = RetrofitFactory().getStudentsServices().getAlunosCurso(sigla)

    call.enqueue(object  : retrofit2.Callback<StudentsList>{

        override fun onResponse(call: Call<StudentsList>, response: Response<StudentsList>) {
            listStudent = response.body()!!.informacoes
            name = response.body()!!.nomeCurso
        }

        override fun onFailure(call: Call<StudentsList>, t: Throwable) {

        }

    })

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "",
                modifier = Modifier.size(80.dp)
            )
            topLine()




        }
    }
}

//@Preview(showBackground = true)
//@Composable
////fun DefaultPreview() {
////    LionSchoolTheme {
////        StudentsScreen(dados)
////    }
////}