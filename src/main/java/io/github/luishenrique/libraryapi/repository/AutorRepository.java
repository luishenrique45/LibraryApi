package io.github.luishenrique.libraryapi.repository;

import io.github.luishenrique.libraryapi.model.Autor;
import io.github.luishenrique.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {
 List<Autor>FindByNome(String nome);
 List<Autor>FindByNacionalidade(String nacionalidade);
 List<Autor>FindByNomeAndNacionalidade(String nome,String nacionalidade);
 Optional<Autor>FindByNomeAndDataNascimentoAndNacionalidade(String nome, LocalDate data_nascimento, String nacionalidade);
}
