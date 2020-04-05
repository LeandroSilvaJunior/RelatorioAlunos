package org.senac.alunos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import org.senac.alunos.aluno.Aluno

class MainActivity : AppCompatActivity() {

    private lateinit var alunoComponent : EditText
    private lateinit var nota1Componente : EditText
    private lateinit var nota2Componente : EditText
    private lateinit var nota3Componente : EditText

    private var alunos : MutableList<Aluno> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alunoComponent = findViewById<EditText>(R.id.et_Aluno)
        nota1Componente = findViewById<EditText>(R.id.et_nota_1)
        nota2Componente = findViewById<EditText>(R.id.et_nota_2)
        nota3Componente = findViewById<EditText>(R.id.et_nota_3)
    }

    fun adicionarAluno(view: View) {
        if (validate()) {
            val aluno = alunoComponent.text.toString()
            val nota1 = nota1Componente.text.toString().toDouble()
            val nota2 = nota2Componente.text.toString().toDouble()
            val nota3 = nota3Componente.text.toString().toDouble()

            alunos.add(Aluno(aluno, nota1, nota2, nota3))

            alunoComponent.setText("")
            nota1Componente.setText("")
            nota2Componente.setText("")
            nota3Componente.setText("")

            Toast.makeText(this, "Aluno adicionado", Toast.LENGTH_SHORT).show()
        }
    }

    fun exibirResultados(view: View) {
        if (validateQuantidadeAlunos()) {
            val alunosAprovados = alunos.filter { a -> a.isAprovado() }.map {a -> a.nome }.toString()
            val mediaAlunos = "%.2f".format(alunos.map { a -> a.getMedia() }.reduce { acc, d -> acc + d } / alunos.count())
            val quantidadeAprovados = alunos.filter { a -> a.isAprovado() }.count()
            val quantidadeReprovados = alunos.filter { a -> !a.isAprovado() }.count()
            val melhorAluno = alunos.maxBy { a -> a.getMedia() }?.nome
            val melhorAlunoNota1 = alunos.maxBy { a -> a.nota1 }?.nome
            val melhorAlunoNota2 = alunos.maxBy { a -> a.nota3 }?.nome
            val melhorAlunoNota3 = alunos.maxBy { a -> a.nota3 }?.nome

            var resultados = StringBuilder()
            resultados.append("Alunos aprovados: ${alunosAprovados}").append("\n")
            resultados.append("Media dos alunos: ${mediaAlunos}").append("\n")
            resultados.append("Quantidade de aprovados: ${quantidadeAprovados}").append("\n")
            resultados.append("Quantidade de reprovados: ${quantidadeReprovados}").append("\n")
            resultados.append("Melhor aluno: ${melhorAluno}").append("\n")
            resultados.append("Melhor aluno na nota 1: ${melhorAlunoNota1}").append("\n")
            resultados.append("Melhor aluno na nota 2: ${melhorAlunoNota2}").append("\n")
            resultados.append("Melhor aluno na nota 3: ${melhorAlunoNota3}").append("\n")

            val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
            alertDialogBuilder.setTitle("Resultados")
            alertDialogBuilder.setMessage(resultados.toString())
            alertDialogBuilder.setNeutralButton("Ok") { _, _->}

            alertDialogBuilder.create().show()
        }
    }

    private fun validateQuantidadeAlunos(): Boolean {
        var validate = true

        if (alunos.count() < 10 ) {
            Toast.makeText(this, "É necessário ter pelo menos 10 alunos cadastrados, atualmente existem ${alunos.count()}", Toast.LENGTH_SHORT).show()
            validate= false
        }

        return validate
    }

    fun validate() : Boolean {
        var validate = true

        if (alunoComponent.text.trim().length == 0) {
            alunoComponent.setError("Você deve informar a aluno")
            validate= false
        }

        if (nota1Componente.text.trim().length == 0) {
            nota1Componente.setError("Você deve informar a nota 1")
            validate= false
        }

        if (nota2Componente.text.trim().length == 0) {
            nota2Componente.setError("Você deve informar a nota 2")
            validate= false
        }

        if (nota3Componente.text.trim().length == 0) {
            nota3Componente.setError("Você deve informar a nota 3")
            validate= false
        }

        return validate
    }

}
