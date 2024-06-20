package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado"
) {

    fun listar(): List<TopicoView> {

        return topicos.stream().map { t -> topicoViewMapper.map(t)}.toList()
    }

    fun buscarPorId(id: Long): TopicoView {


        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow{
            NotFoundException(notFoundMessage)
        }

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView {

        val topico = topicoFormMapper.map(dto)

        topico.id = topicos.size.toLong() + 1

        topicos = topicos.plus(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(novoTopico: AtualizacaoTopicoForm): TopicoView {

        val topico = topicos.stream().filter { t ->
            t.id == novoTopico.id
        }.findFirst().orElseThrow{
            NotFoundException(notFoundMessage)
        }

        val topicoAtualizado = Topico(id = novoTopico.id, titulo = novoTopico.titulo, mensagem = novoTopico.mensagem, curso = topico.curso, autor = topico.autor, respostas = topico.respostas, status = topico.status, dataCriacao = topico.dataCriacao)

        topicos = topicos.minus(topico).plus(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()//orElseThrow{
//            NotFoundException(notFoundMessage)
//        }

        topicos = topicos.minus(topico)
    }
}