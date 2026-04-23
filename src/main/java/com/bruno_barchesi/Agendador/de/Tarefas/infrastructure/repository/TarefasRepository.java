package com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.repository;

import com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<TarefaEntity, String> {



}
