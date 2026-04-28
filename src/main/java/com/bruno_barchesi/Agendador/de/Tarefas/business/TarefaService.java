package com.bruno_barchesi.Agendador.de.Tarefas.business;

import com.bruno_barchesi.Agendador.de.Tarefas.business.converter.TarefaConverter;
import com.bruno_barchesi.Agendador.de.Tarefas.business.dto.TarefaDTO;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.entity.TarefaEntity;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.repository.TarefasRepository;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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



    //Encontrando todas as tarefas entre um determinado periodo (PARA USAR NO SCHEDULE DE NOTIFIACAO)
    public List<TarefaDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
     return tarefaConverter.paraListaTarefaDTO(tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));

    }



    //Encontrando tarefas de um usuario pelo email extraido do token:
    public List<TarefaDTO> encontrarTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        List<TarefaEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);
        return tarefaConverter.paraListaTarefaDTO(listaTarefas);
    }




    //Metodo de deletar tarefa por ID
    public void deletarPorId(String id){
        tarefasRepository.deleteById(id);
    }




    //ALTERAR O STATUS DA TAREFA COM PATCH:
    public TarefaDTO alterarStatusTarefa(StatusNotificacaoEnum status, String id){
        try {
            TarefaEntity tarefa = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa nao encontrada"));

            tarefa.setStatusNotificacao(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefa));
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }




    //ATUALIZANDO TAREFA COMPLETAMENTE:
    public TarefaDTO atualizarTarefa(String id, TarefaDTO tarefaDTO){
        try {
            TarefaEntity tarefa = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa nao encontrada com esse id: " + id));

            TarefaDTO tarefaAtualizadaDTO = tarefaConverter.atualizarTarefa(tarefa, tarefaDTO);
            tarefasRepository.save(tarefaConverter.paraTarefaEntity(tarefaAtualizadaDTO));
            return tarefaAtualizadaDTO;

        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao atualizar tarefa: " + e.getCause());
        }




    }



}
