package com.entrevista.builders.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDomain implements Serializable {


    private Long id;

    private String rua;

    private Integer numero;

    private String bairro;

    private Integer cep;

    private String complemento;

    private CidadeDomain cidade;

}
