package com.bruno_barchesi.Agendador.de.Tarefas.business.converter;

import com.bruno_barchesi.Agendador.de.Tarefas.business.dto.TarefaDTO;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.entity.TarefaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

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




    //LISTA DE TAREFAS ENTITY PARA LISTA TAREFAS DTO:
    public List<TarefaDTO> paraListaTarefaDTO(List<TarefaEntity> tarefaEntities){
        return tarefaEntities.stream().map(this::paraTarefaDTO).toList();
    }




    //UPDATE DE TAREFA:
    public TarefaDTO atualizarTarefa(TarefaEntity tarefaEntity, TarefaDTO tarefaDTO){
        return TarefaDTO.builder()
                .nomeTarefa(tarefaDTO.getNomeTarefa() != null ? tarefaDTO.getNomeTarefa() : tarefaEntity.getNomeTarefa())
                .id(tarefaEntity.getId())
                .statusNotificacao(tarefaDTO.getStatusNotificacao() != null ? tarefaDTO.getStatusNotificacao() : tarefaEntity.getStatusNotificacao())
                .descricao(tarefaDTO.getDescricao() != null ? tarefaDTO.getDescricao() : tarefaEntity.getDescricao())
                .emailUsuario(tarefaDTO.getEmailUsuario() != null ? tarefaDTO.getEmailUsuario() : tarefaEntity.getEmailUsuario())
                .dataCriacao(tarefaDTO.getDataCriacao() != null ? tarefaDTO.getDataCriacao() : tarefaEntity.getDataCriacao())
                .dataEvento(tarefaDTO.getDataEvento() != null ? tarefaDTO.getDataEvento() : tarefaEntity.getDataEvento())
                .dataAlteracao(tarefaDTO.getDataAlteracao() != null ? tarefaDTO.getDataAlteracao() : tarefaEntity.getDataAlteracao())
                .build();



    }



}
