package com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.repository;

import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefaEntity, String> {

    //Encontrando todas as tarefas entre um determinado periodo:
    List<TarefaEntity> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);


    //Encontrando tarefas por email:
    List<TarefaEntity> findByEmailUsuario(String email);

}
