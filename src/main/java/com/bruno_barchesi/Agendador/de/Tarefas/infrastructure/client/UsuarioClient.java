package com.bruno_barchesi.Agendador.de.Tarefas.infrastructure.client;

import com.bruno_barchesi.Agendador.de.Tarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${url.usuario}")
//name é o nome que quero dar a minha api, e url é a url dela, que nesse caso coloquei para apontar pra uma variavel em /.properties
public interface UsuarioClient {

    @GetMapping
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam("email") String email,
                                     @RequestHeader("Authorization") String token);


}
