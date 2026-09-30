package io.github.luishenrique.libraryapi.validator;

import io.github.luishenrique.libraryapi.exceptions.ResgistroDuplicado;
import io.github.luishenrique.libraryapi.model.Autor;
import io.github.luishenrique.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class ValidadorAutor {
   private final AutorRepository repository;

    public ValidadorAutor(AutorRepository repository) {
        this.repository = repository;
    }
  public void validar(Autor autor){
  if(existeAutorCadastratado(autor)){
      throw  new ResgistroDuplicado("Autor ja cadastrado");
  }

  }
  public boolean existeAutorCadastratado(Autor autor){
        Optional<Autor> autorEncontrado = repository.FindByNomeAndDataNascimentoAndNacionalidade( autor.getNome(),autor.getData_nascimento(),autor.getNacionalidade());
        if(autor.getId()==null){
            return autorEncontrado.isPresent();
        }
        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
  }

}
