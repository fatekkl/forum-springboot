package br.com.alura.forum.service

import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service


@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val cursoService: CursoService,
    private val autorService: UsuarioService,
) {


    // memory list to simulate database


    fun listar(): List<TopicoView> {

        return topicos.stream().map { t -> TopicoView(t.id, t.titulo, t.mensagem, t.status, t.dataCriacao) }.toList()
    }

    fun buscarPorId(id: Long): TopicoView {


        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()

        return TopicoView(
            topico.id,
            topico.titulo,
            topico.mensagem,
            topico.status,
            topico.dataCriacao
        )

    }

    fun cadastrar(dto: NovoTopicoForm) {
        topicos = topicos.plus(
            Topico(
                id = topicos.size.toLong() + 1,
                titulo = dto.titulo,
                mensagem = dto.mensagem,
                curso = cursoService.buscarPorId(dto.idCurso),
                autor = autorService.buscarPorId(dto.idAutor)
            )
        )
    }
}