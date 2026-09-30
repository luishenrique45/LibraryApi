package io.github.luishenrique.libraryapi.repository;


import io.github.luishenrique.libraryapi.model.Autor;
import io.github.luishenrique.libraryapi.model.Generolivro;
import io.github.luishenrique.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class LivroRepositoryTest {
   @Autowired
    LivroRepository repository;
   @Autowired
     AutorRepository repositoryA;
 @Test
   void saivar(){
       Livro livro = new Livro();
       livro.setIsbn("90909-9999");
       livro.setPreco(BigDecimal.valueOf(100,00));
       livro.setGenero(Generolivro.FICCAO);
       livro.setTitulo("UFO");
       livro.setData_publicacao(LocalDate.of(1980,1,2));

       Autor autor1 = new Autor();
       autor1.setNome("maria3");
       autor1.setNacionalidade("Bresileira");
       autor1.setData_nascimento(LocalDate.of(1951,1,31));
       repositoryA.save(autor1);
       livro.setAutor(autor1);

       repository.save(livro);
   }

}