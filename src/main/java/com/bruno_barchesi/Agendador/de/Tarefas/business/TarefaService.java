package com.bruno_barchesi.Agendador.de.Tarefas.business;

import com.bruno_barchesi.Agendador.de.Tarefas.business.converter.TarefaConverter;
import com.bruno_barchesi.Agendador.de.Tarefas.business.dto.TarefaDTO;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.entity.TarefaEntity;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.repository.TarefasRepository;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService{

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;



    //GRAVANDO UMA TAREFA:
    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO){
        String email = jwtUtil.extrairEmailDoToken(token.substring(7)); //buscando email do usuario pelo token
        tarefaDTO.setEmailUsuario(email); //setando automaticamente na tarefa o email dele.

        tarefaDTO.setDataCriacao(LocalDateTime.now()); //seta a criaçao pegando a hora de agora do sistema
        tarefaDTO.setStatusNotificacao(StatusNotificacaoEnum.PENDENTE); //Seta o status de notificacao como pendente pq acabou de criar a tarefa.

        TarefaEntity tarefaEntity = tarefasRepository.save(tarefaConverter.paraTarefaEntity(tarefaDTO));
        return tarefaConverter.paraTarefaDTO(tarefaEntity);

    }


}
