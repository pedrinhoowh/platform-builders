package com.entrevista.builders.mapper;

import com.entrevista.builders.domain.UfDomain;
import com.entrevista.builders.model.UfModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UfMapper {

    UfDomain toDomain(UfModel ufModel);

    UfModel toModel(UfDomain ufDomain);

}
