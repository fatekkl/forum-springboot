package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service


@Service
class TopicoService(private var topicos: List<Topico>) {


    // memory list to simulate database
    init {
        val curso = Curso(1,"teste", "teste")
        val user = Usuario(1, "temten", "aaaa")

        val topico = Topico(1, "teste", "teste1", curso = curso, autor =  user)
        val topico2 = Topico(2,"teste2", "teste2", curso = curso, autor = user )

        topicos = listOf(topico, topico2)
    }

    fun listar(): List<Topico> {

        return topicos
    }

    fun buscarPorId(id: Long): Topico {


        return topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()


    }
}