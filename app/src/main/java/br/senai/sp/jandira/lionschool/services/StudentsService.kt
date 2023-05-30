package br.senai.sp.jandira.lionschool.services


import br.senai.sp.jandira.lionschool.model.StudentsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentsService {

    @GET("alunos")
    fun getAlunos() : retrofit2.Call<StudentsList>

    @GET("alunos/")
    fun getAlunosCurso(
        @Query("curso") curso: String
    ): Call<StudentsList>

    @GET("alunos/")
    fun getAlunosStatus(
        @Query("status") status: String
    ): Call<StudentsList>

    @GET("alunos/")
    fun getAlunosStatusCurso(
        @Query("curso") curso: String,
        @Query("status") status: String
    ): Call<StudentsList>
}