package io.github.luishenrique.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor",schema = "public")
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Autor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "nome",length =100,nullable = false)
    private String nome;
    @Column(name = "data_nascimento",nullable = false)
    private LocalDate data_nascimento;
    @Column(name = "nacionalidade",length = 50,nullable = false)
    private String nacionalidade;
   @OneToMany(mappedBy = "autor" ,cascade = CascadeType.ALL)
    private List<Livro> livros;

   @CreatedDate
   @Column(name ="dataCadastro")
    private LocalDateTime dataCadastro;

   @LastModifiedDate
   @Column(name ="dataAtualizacao")
    private LocalDateTime dataAtualizacao ;
    private UUID idUsuario;
}
