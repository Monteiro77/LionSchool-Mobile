package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.topLine
import br.senai.sp.jandira.lionschool.model.CoursesList
import br.senai.sp.jandira.lionschool.services.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

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
            mutableStateOf("")
        }

        val call = RetrofitFactory().getCoursesServices().getCurses()

        call.enqueue(object : retrofit2.Callback<CoursesList>{
            override fun onResponse(
                call: Call<CoursesList>,
                response: Response<CoursesList>) {

            }


        })

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)

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

                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.agenda),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.course),
                        fontSize = 15.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 18.sp
                    )
                }

                LazyColumn(){
                    items()
                    Card(
                        backgroundColor = Color(51,71,156)

                    ) {

                    }
                }




            }
        }
}