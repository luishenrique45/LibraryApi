package io.github.luishenrique.libraryapi.service;

import io.github.luishenrique.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.luishenrique.libraryapi.model.Autor;
import io.github.luishenrique.libraryapi.repository.AutorRepository;
import io.github.luishenrique.libraryapi.repository.LivroRepository;
import io.github.luishenrique.libraryapi.validator.ValidadorAutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository repository;
    private final ValidadorAutor validar;
    private final LivroRepository livroRepository;

    public AutorService(AutorRepository repository, ValidadorAutor validar, LivroRepository livroRepository) {
        this.repository = repository;
        this.validar = validar;
        this.livroRepository = livroRepository;
    }

    public Autor salvar(Autor autor){
        validar.validar(autor);
        return repository.save(autor);
    }
    public void atualizar(Autor autor){
    if(autor.getId()==null){
        throw new IllegalArgumentException("para atualizar e necessario existir o autor");
    }
         validar.validar(autor);
         repository.save(autor);
    }
    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Autor autor){
        if(possuiLivro(autor)){
            throw new OperacaoNaoPermitidaException("Autor possui livros cadastrados!");
        }
        repository.delete(autor);
    }
    public List<Autor> pesquisar(String nome , String nacionalidade){
        if(nome !=null && nacionalidade !=null){
            return repository.FindByNomeAndNacionalidade(nome,nacionalidade);
        }
        if(nome != null){
            return repository.FindByNome(nome);
        }
        if(nacionalidade != null ){
            return repository.FindByNacionalidade(nacionalidade);
        }
        return repository.findAll();
    }
public boolean possuiLivro(Autor autor){
     return livroRepository.existsByAutor(autor);
}

}
