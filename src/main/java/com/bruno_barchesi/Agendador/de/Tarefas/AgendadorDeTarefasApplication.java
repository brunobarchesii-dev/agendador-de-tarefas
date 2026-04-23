package com.bruno_barchesi.Agendador.de.Tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //para usar o FEIGN preciso anotar aqui tmb
public class AgendadorDeTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendadorDeTarefasApplication.class, args);
	}

}
