package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
data class UsuarioService(var autores: List<Usuario>) {

    init {
        val autor = Usuario(
            1,
            "Matheus",
            "mathtml.1105@gmail.com"
        )

        autores = listOf(autor)
    }

    fun buscarPorId(idAutor: Long): Usuario {
        return autores.stream().filter { a ->
            a.id == idAutor
        }.findFirst().get()
    }
}