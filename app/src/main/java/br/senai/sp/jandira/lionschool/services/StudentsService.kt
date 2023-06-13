package br.senai.sp.jandira.lionschool.services


import br.senai.sp.jandira.lionschool.model.StudentGrade
import br.senai.sp.jandira.lionschool.model.StudentsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentsService {

    @GET("alunos")
    fun getAlunos() : retrofit2.Call<StudentsList>

    @GET("alunos/")
    fun getAlunosCurso(
        @Query("curso") curso: String
    ): retrofit2.Call<br.senai.sp.jandira.lionschool.model.StudentsList>

    @GET("alunos/{matricula}")
    fun getAlunoByMatrciula(
        @Path("matricula") matricula: String
    ): Call<StudentGrade>
}