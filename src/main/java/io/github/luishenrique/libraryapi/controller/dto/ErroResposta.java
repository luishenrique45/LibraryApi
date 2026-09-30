package io.github.luishenrique.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroResposta> erros ) {

    public static ErroResposta respostaRadrao(String mensagem){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(),mensagem,List.of());
    }
 public static ErroResposta conflito(String mensagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(),mensagem,List.of());
 }

}
