package io.github.luishenrique.libraryapi.repository;


import io.github.luishenrique.libraryapi.model.Autor;
import io.github.luishenrique.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;
    @Autowired
    LivroRepository livroRepository;

  @Test
  public void salvarTest(){

      Autor autor = new Autor();
      autor.setNome("maria");
      autor.setNacionalidade("Bresileira");
      autor.setData_nascimento(LocalDate.of(1951,1,31));

      var autorSalvo = repository.save(autor);
      System.out.println("autor salvo"+ autorSalvo);
  }
@Test
public void atualizarTest() {
    var id = UUID.fromString("232b45a1-0efa-467f-bc80-b750124db314");
    Optional<Autor> possivelAutor = repository.findById(id);

    if (possivelAutor.isPresent()) {

        Autor autorEncontrado = possivelAutor.get();
        System.out.println("dados do autor:");
        System.out.println(autorEncontrado);

        autorEncontrado.setData_nascimento(LocalDate.of(1960, 1, 30));
        repository.save(autorEncontrado);
    }
}

@Test
public void listarTest(){

       List<Autor> lista = repository.findAll();
       lista.forEach(System.out::println);
    }
@Test
public void countTest(){
      System.out.println("contagem de autores" + repository.count()  );
}
@Test
public void deletarPorIdTest(){
   var id = UUID.fromString("232b45a1-0efa-467f-bc80-b750124db314");
   repository.deleteById(id);


}


}
