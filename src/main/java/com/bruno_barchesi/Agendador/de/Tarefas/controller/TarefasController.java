package com.bruno_barchesi.Agendador.de.Tarefas.controller;

import com.bruno_barchesi.Agendador.de.Tarefas.business.TarefaService;
import com.bruno_barchesi.Agendador.de.Tarefas.business.dto.TarefaDTO;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefaService tarefaService;


    //gravando tarefa:
    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO tarefaDTO,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
    }


    //Retornando lista de tarefa por periodo:
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscarTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal){
        return ResponseEntity.ok(tarefaService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }
    //EXPLICACAO DO @DATETIMEFORMAT NO NOTION.



    //RETORNANDO LISTA DE TAREFAS BUSCANDO POR EMAIL
    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.encontrarTarefasPorEmail(token));
    }




    //deletar por id:
    @DeleteMapping
    public ResponseEntity<Void> deletarPorId(@RequestParam("id") String id){
        try {
            tarefaService.deletarPorId(id);
            return ResponseEntity.ok().build();
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("ID nao encontrado: " + id );
        }
    }




    //ATUALIZAR STATUS NOTIFICACAO
    @PatchMapping
    public ResponseEntity<TarefaDTO> atualizarStatusNotificacao(@RequestParam("status")StatusNotificacaoEnum status,
                                                                @RequestParam("id") String id){
        return ResponseEntity.ok(tarefaService.alterarStatusTarefa(status, id));
    }




    //ATUALIZAR TAREFA COMPLETAMENTE:
    @PutMapping
    public ResponseEntity<TarefaDTO> atualizarTarefa(@RequestBody TarefaDTO tarefaDTO,
                                                     @RequestParam("id") String id){
        return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefaDTO));
    }




}
