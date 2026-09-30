package io.github.luishenrique.libraryapi.repository;

import io.github.luishenrique.libraryapi.model.Autor;
import io.github.luishenrique.libraryapi.model.Generolivro;
import io.github.luishenrique.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {

    boolean existsByAutor(Autor autor);

    List<Livro> findByAutor(Autor autor);
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);
    //jpql//
    @Query("select l from Livro as l order by l.titulo,l.preco ")
    List<Livro> ListarTodosOrdenandoPortituloAndPreco();
  @Query("""
 select l.genero
 from Livro l
 join l.autor a
 where a.nacionalidade ='Brasileira'
 order by l.genero
""")
  List<String>listargenerosAutoresBrasileira();
@Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao")
    List<Livro>findByGenero(@Param("genero")Generolivro generolivro,
                            @Param("paramOrdenacao")String nomePropiedade);

@Query("select l from Livro l where l.genero = ?1 order by ?2")
List<Livro>findByGeneroposi(Generolivro generolivro, String nomePropiedade);
@Modifying
@Transactional
@Query("delete from Livro set dataPublicacao = ?1")
void deleteByGenero(Generolivro genero);

    @Modifying
    @Transactional
    @Query("update Livro set dataPublicacao = ?1")
    void updateDataPublicacao(LocalDate novaData);
}
