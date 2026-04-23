package com.bruno_barchesi.Agendador.de.Tarefas.business.converter;

import com.bruno_barchesi.Agendador.de.Tarefas.business.dto.TarefaDTO;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.entity.TarefaEntity;
import org.springframework.stereotype.Component;

@Component
public class TarefaConverter {

    public TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO){
        return TarefaEntity.builder()
                .nomeTarefa(tarefaDTO.getNomeTarefa())
                .dataEvento(tarefaDTO.getDataEvento())
                .id(tarefaDTO.getId())
                .dataCriacao(tarefaDTO.getDataCriacao())
                .descricao(tarefaDTO.getDescricao())
                .dataAlteracao(tarefaDTO.getDataAlteracao())
                .statusNotificacao(tarefaDTO.getStatusNotificacao())
                .emailUsuario(tarefaDTO.getEmailUsuario())
                .build();

    }


    public TarefaDTO paraTarefaDTO(TarefaEntity tarefa){
        return TarefaDTO.builder()
                .nomeTarefa(tarefa.getNomeTarefa())
                .dataEvento(tarefa.getDataEvento())
                .id(tarefa.getId())
                .dataCriacao(tarefa.getDataCriacao())
                .descricao(tarefa.getDescricao())
                .dataAlteracao(tarefa.getDataAlteracao())
                .statusNotificacao(tarefa.getStatusNotificacao())
                .emailUsuario(tarefa.getEmailUsuario())
                .build();
    }


}
