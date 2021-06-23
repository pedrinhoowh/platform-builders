package com.entrevista.builders.mapper;

import com.entrevista.builders.domain.CidadeDomain;
import com.entrevista.builders.model.CidadeModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UfMapper.class)
public interface CidadeMapper {

    CidadeDomain toDomain(CidadeModel cidadeModel);

    CidadeModel toModel(CidadeDomain cidadeDomain);

}
