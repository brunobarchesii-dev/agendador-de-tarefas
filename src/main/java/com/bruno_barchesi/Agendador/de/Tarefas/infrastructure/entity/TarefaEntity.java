package com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.entity;

import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//CRIACAO USANDO MONGODB - BANCO DE DADOS NO SQL:

@Document("tarefa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TarefaEntity {

    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacao;

}
