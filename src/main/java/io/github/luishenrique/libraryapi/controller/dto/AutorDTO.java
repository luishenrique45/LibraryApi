package io.github.luishenrique.libraryapi.controller.dto;

import io.github.luishenrique.libraryapi.model.Autor;

import java.time.LocalDate;

public record AutorDTO(
        String nome,
        LocalDate dataNascimento,
        String nacionalidade
) {
    public Autor mapearAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setData_nascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;

    }
}
