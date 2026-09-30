package io.github.luishenrique.libraryapi.controller;

import io.github.luishenrique.libraryapi.controller.dto.AutorDTO;

import io.github.luishenrique.libraryapi.controller.dto.AutorRespostaDto;
import io.github.luishenrique.libraryapi.controller.dto.ErroResposta;
import io.github.luishenrique.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.luishenrique.libraryapi.exceptions.ResgistroDuplicado;
import io.github.luishenrique.libraryapi.model.Autor;
import io.github.luishenrique.libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("autores")
public class AutorController {

    private AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody AutorDTO autor) {
        try {
            var autorEntidade = autor.mapearAutor();
            service.salvar(autorEntidade);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(autorEntidade.getId())
                    .toUri();

            return new ResponseEntity("autor salvo com sucesso" + autor, HttpStatus.CREATED);
        } catch (ResgistroDuplicado e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<AutorRespostaDto> obterDetalhes(@PathVariable("id") String id) {
        var autorId = UUID.fromString(id);
        Optional<Autor> autorOptinal = service.obterPorId(autorId);
        if (autorOptinal.isPresent()) {
            Autor autor = autorOptinal.get();
            AutorRespostaDto dto = new AutorRespostaDto(
                    autor.getId(), autor.getNome(), autor.getData_nascimento(), autor.getNacionalidade()

            );
            return ResponseEntity.ok(dto);

        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable String id) {
        try {
            var autorID = UUID.fromString(id);
            Optional<Autor> autorOP = service.obterPorId(autorID);
            if (autorOP.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (OperacaoNaoPermitidaException e) {
            var erroR = ErroResposta.respostaRadrao(e.getMessage());
            return ResponseEntity.status(erroR.status()).body(erroR);
        }
    }

    @GetMapping
    public ResponseEntity<List<AutorRespostaDto>> pesquizar(@RequestParam(value = "nome", required = false) String nome, @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {
        List<Autor> resultado = service.pesquisar(nome, nacionalidade);
        List<AutorRespostaDto> lista = resultado.stream().
                map(autor -> new AutorRespostaDto(
                        autor.getId()
                        , autor.getNome()
                        , autor.getData_nascimento()
                        , autor.getNacionalidade())
                ).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable String id, @RequestBody AutorRespostaDto dto) {
        try {
            var iD = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(iD);
            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            var autor = autorOptional.get();
            autor.setNome(dto.nome());
            autor.setNacionalidade(dto.nacionalidade());
            autor.setData_nascimento(dto.dataNascimento());

            service.atualizar(autor);
            return ResponseEntity.noContent().build();
        } catch (ResgistroDuplicado e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }

    }
}
