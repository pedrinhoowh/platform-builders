package com.entrevista.builders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_uf")
@AllArgsConstructor
@NoArgsConstructor
public class UfModel implements Serializable {

    private static final long serialVersionUID = -5455046264148552016L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "dsc_nome")
    private String nome;

    @NotNull
    @Size(max = 2)
    @Column(name = "dsc_sigla")
    private String sigla;

}
