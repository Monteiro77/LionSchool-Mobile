package br.senai.sp.jandira.lionschool.services

import br.senai.sp.jandira.lionschool.model.CoursesList
import retrofit2.Call
import retrofit2.http.GET

interface CoursesService {

    @GET("cursos")
    fun getCurses() : Call<CoursesList>

}