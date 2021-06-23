package com.entrevista.builders.repository;

import com.entrevista.builders.enums.TipoClienteEnum;
import com.entrevista.builders.model.ClienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteModel, String> {

    @Query(value = "SELECT c FROM ClienteModel c WHERE (:tipoClienteEnum is null or c.tipoCliente.tipoCliente = :tipoClienteEnum) " +
            "AND (:cidade is null or lower(c.endereco.cidade.nome) like lower(concat('%', :cidade,'%'))) " +
            "AND (:siglaEstado is null or lower(c.endereco.cidade.uf.sigla) like lower(concat('%', :siglaEstado,'%')))")
    Page<ClienteModel> findAll(@Param("tipoClienteEnum") TipoClienteEnum tipoClienteEnum, @Param("siglaEstado") String siglaEstado,
                               @Param("cidade") String cidade, Pageable pageable);

}
