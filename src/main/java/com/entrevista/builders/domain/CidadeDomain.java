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
public class CidadeDomain implements Serializable {

    private static final long serialVersionUID = -2508414861402621232L;
    private Long id;

    private String nome;

    private UfDomain uf;
}
