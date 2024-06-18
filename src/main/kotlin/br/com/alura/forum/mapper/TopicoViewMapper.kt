package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Component


@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {

    override fun map(obj: Topico): TopicoView {
        return TopicoView(
            obj.id,
            obj.titulo,
            obj.mensagem,
            obj.status,
            obj.dataCriacao
        )
    }
}