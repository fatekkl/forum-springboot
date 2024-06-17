package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service


@Service
class TopicoService {


    fun listar(): List<Topico> {

        val curso = Curso(1,"teste", "teste")
        val user = Usuario(1, "temten", "aaaa")
        val topico = Topico(1, "teste", "teste2", curso = curso, autor =  user)


        return listOf(topico)
    }
}