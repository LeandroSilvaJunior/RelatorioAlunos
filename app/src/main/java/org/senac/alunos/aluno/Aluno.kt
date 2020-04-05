package org.senac.alunos.aluno

class Aluno(val nome : String, val nota1 : Double, val nota2 : Double, val nota3 : Double) {

    fun isAprovado() : Boolean {
        return getMedia() > 7
    }

    fun getMedia(): Double {
        return (nota1 + nota2 + nota3)/3
    }
}