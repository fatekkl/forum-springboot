package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val autorService: UsuarioService,
) : Mapper<NovoTopicoForm, Topico> {

    override fun map(obj: NovoTopicoForm): Topico {
        return Topico(
            titulo = obj.titulo,
            mensagem = obj.mensagem,
            curso = cursoService.buscarPorId(obj.idCurso),
            autor = autorService.buscarPorId(obj.idAutor)
        )
    }
}
