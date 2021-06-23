package com.entrevista.builders.repository;

import com.entrevista.builders.model.CidadeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends CrudRepository<CidadeModel, Long> {

    Optional<CidadeModel> findByNomeAndUf_Sigla(String nome, String sigla);

}
