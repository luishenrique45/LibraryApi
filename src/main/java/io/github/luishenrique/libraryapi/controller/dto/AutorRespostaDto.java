package io.github.luishenrique.libraryapi.controller.dto;

import io.github.luishenrique.libraryapi.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

public record AutorRespostaDto(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade
) {

    public Autor mapearRespostaAutor(){
        Autor autor = new Autor();
        autor.setId(this.id);
        autor.setNome(this.nome);
        autor.setData_nascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);

        return autor;
    }

}
