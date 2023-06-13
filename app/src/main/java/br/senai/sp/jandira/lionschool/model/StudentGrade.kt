package br.senai.sp.jandira.lionschool.model

data class StudentGrade(

    val nome: String,
    val foto: String,
    val matricula: String,
    val sexo: String,
    val status: String,
    val nomeCurso : String,
    val sigla: String,
    val icone: String,
    val conclusao: String,
    val disciplinas: List<Discipline>


)
