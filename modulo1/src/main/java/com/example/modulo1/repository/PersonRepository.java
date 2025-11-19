package com.example.modulo1.repository;

import com.example.modulo1.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de acesso à entidade Pessoa.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    /**
     * Retorna apenas as pessoas ativas de forma paginada.
     *
     * @param pageable informações de paginação
     * @return página de pessoas ativas
     */
    Page<Person> findByAtivoTrue(Pageable pageable);
}