package com.entrevista.builders.repository;

import com.entrevista.builders.model.EnderecoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<EnderecoModel, Long> {
}
