package com.entrevista.builders.mapper;

import com.entrevista.builders.domain.ClienteDomain;
import com.entrevista.builders.model.ClienteModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class})
public interface ClienteMapper {

    @Mapping(source = "tipoCliente.tipoCliente", target = "tipoCliente")
    ClienteDomain toDomain(ClienteModel clienteModel);

    @Mapping(source = "tipoCliente", target = "tipoCliente.tipoCliente")
    ClienteModel toModel(ClienteDomain clienteDomain);

}
