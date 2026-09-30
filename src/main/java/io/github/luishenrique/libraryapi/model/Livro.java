package io.github.luishenrique.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "livro", schema = "public")
@Data
public class Livro {
    @Id
    @Column(name = "id",nullable = false )
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "isbn",length = 20,nullable = false)
    private String isbn;
    @Column(name = "TITULO",length = 150,nullable = false)
    private String titulo;
    @Column(name = "data_publicacao",nullable = false)
    private LocalDate data_publicacao;
    @Enumerated(EnumType.STRING)
    @Column(name = "genero",nullable = false)
    private Generolivro genero ;
    @Column(name = "preco",precision = 18,scale = 2)
    private BigDecimal preco;
    @ManyToOne()
    @JoinColumn(name = "id_autor",nullable = false)
    private Autor autor;

}
