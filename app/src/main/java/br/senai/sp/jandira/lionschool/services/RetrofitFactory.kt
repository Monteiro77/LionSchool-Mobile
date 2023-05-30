package br.senai.sp.jandira.lionschool.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val BASE_URL = "https://api-lion-school-2023.cyclic.app/v1/lion-school/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create())
        .build()

    fun  getCoursesServices(): CoursesService {
        return  retrofitFactory.create(CoursesService::class.java)
    }

    fun getStudentsServices(): StudentsService{
        return  retrofitFactory.create(StudentsService::class.java)
    }
}