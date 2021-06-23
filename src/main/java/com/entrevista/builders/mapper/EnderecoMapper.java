package com.entrevista.builders.mapper;

import com.entrevista.builders.domain.EnderecoDomain;
import com.entrevista.builders.model.EnderecoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CidadeMapper.class)
public interface EnderecoMapper {

    EnderecoDomain toDomain(EnderecoModel enderecoModel);

    EnderecoModel toModel(EnderecoDomain enderecoDomain);

}
