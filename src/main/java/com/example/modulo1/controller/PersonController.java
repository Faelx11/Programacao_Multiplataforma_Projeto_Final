package com.example.modulo1.controller;

import com.example.modulo1.model.Person;
import com.example.modulo1.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * Controlador REST para operações de CRUD de Pessoa.
 */
@RestController
@RequestMapping("/pessoas")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Lista pessoas ativas de forma paginada.
     *
     * @param page página desejada (começando em 0)
     * @param size quantidade de itens por página (padrão 10)
     * @return página de pessoas ativas
     */
    @GetMapping
    public Page<Person> list(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        return personService.listActive(page, size);
    }

    /**
     * Busca uma pessoa por ID.
     *
     * @param id identificador da pessoa
     * @return pessoa encontrada ou 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable Long id) {
        Optional<Person> person = personService.getById(id);
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cria uma nova pessoa.
     *
     * @param person corpo da requisição
     * @return pessoa criada
     */
    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        Person saved = personService.save(person);
        return ResponseEntity.created(URI.create("/pessoas/" + saved.getId())).body(saved);
    }

    /**
     * Atualiza uma pessoa existente.
     *
     * @param id identificador da pessoa
     * @param person pessoa com dados atualizados
     * @return pessoa atualizada ou 404
     */
    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
        Optional<Person> existing = personService.getById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        person.setId(id);
        return ResponseEntity.ok(personService.save(person));
    }

    /**
     * Exclui uma pessoa.
     *
     * @param id identificador da pessoa
     * @return resposta sem conteúdo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (personService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}