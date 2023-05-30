package br.senai.sp.jandira.lionschool

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.cardLine
import br.senai.sp.jandira.lionschool.components.mediumLine
import br.senai.sp.jandira.lionschool.components.topLine
import br.senai.sp.jandira.lionschool.model.Course
import br.senai.sp.jandira.lionschool.model.CoursesList
import br.senai.sp.jandira.lionschool.services.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.math.log

class CousersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                CousersScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CousersScreen() {

        var searchState by remember {
            mutableStateOf("")
        }

        var listCourses by remember {
            mutableStateOf(listOf<Course>())
        }

        //criando a chamda do end point
        val call = RetrofitFactory().getCoursesServices().getCurses()

        //executando a chamada
        call.enqueue(object : retrofit2.Callback<CoursesList>{
            override fun onResponse(
                call: Call<CoursesList>,
                response: Response<CoursesList>
            ) {
                listCourses = response.body()!!.cursos
            }
            override fun onFailure(call: Call<CoursesList>, t: Throwable) {
                Log.i("ds2t", "onFailure: ${t.message}")
            }
        })

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id = R.drawable.logo), contentDescription = null,  modifier = Modifier.size(78.dp))

                    topLine()

                    OutlinedTextField(
                        modifier = Modifier
                            .width(300.dp)
                            .padding(end = 10.dp),
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
                        }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 23.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.agenda),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.course),
                        fontSize = 15.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 18.sp,
                        color = Color(51,71,176)
                    )
                }
                LazyColumn(){
                    items(listCourses){
                        Card(
                            backgroundColor = Color(51,71,176),
                            modifier = Modifier
                                .width(400.dp)
                                .height(200.dp)
                                .padding(horizontal = 25.dp, vertical = 10.dp),
                            shape = RoundedCornerShape(30.dp),

                        ) {
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(top = 5.dp, start = 5.dp)
                            ){
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                    .padding(end = 180.dp, bottom = 10.dp)
                                    .fillMaxWidth()) {
                                    AsyncImage(
                                        model = it.icone,
                                        contentDescription = "Icone do ${it.nome}",
                                        modifier = Modifier.size(80.dp),
                                        colorFilter = ColorFilter.tint(Color.White)
                                    )
                                    Text(text = it.sigla, color = Color.White, fontSize = 40.sp, modifier = Modifier.padding(start = 10.dp))

                                }
                                cardLine()
                                
                                Text(text = it.nome, color = Color.White, modifier = Modifier.padding(top = 6.dp), fontWeight = FontWeight.Bold)
                                
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)) {
                                    Image(painter = painterResource(id = R.drawable.baseline_watch_later_24), contentDescription = "" )
                                    Text(text = "${it.carga} horas", color = Color.White)
                                }
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(80.dp))
                topLine()
                Row() {
                    Image(painter = painterResource(id = R.drawable.icons_social), contentDescription = "", modifier = Modifier.size(190.dp))
                }
            }
        }
}


