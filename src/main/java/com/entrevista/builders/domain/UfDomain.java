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
public class UfDomain implements Serializable {

    private static final long serialVersionUID = -5760002756143000536L;
    private Long id;

    private String nome;

    private String sigla;

}
