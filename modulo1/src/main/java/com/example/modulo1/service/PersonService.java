package com.example.modulo1.service;

import com.example.modulo1.model.Person;
import com.example.modulo1.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Serviço com regras de negócio da entidade Pessoa.
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Retorna página de pessoas ativas.
     *
     * @param page número da página (zero-based)
     * @param size tamanho da página
     * @return página de pessoas ativas
     */
    public Page<Person> listActive(int page, int size) {
        return personRepository.findByAtivoTrue(PageRequest.of(page, size));
    }

    /**
     * Busca uma pessoa por ID.
     *
     * @param id identificador da pessoa
     * @return pessoa, se existir
     */
    public Optional<Person> getById(Long id) {
        return personRepository.findById(id);
    }

    /**
     * Cria ou atualiza uma pessoa.
     *
     * @param person entidade a ser salva
     * @return pessoa persistida
     */
    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    /**
     * Remove uma pessoa por ID.
     *
     * @param id identificador da pessoa
     */
    @Transactional
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}