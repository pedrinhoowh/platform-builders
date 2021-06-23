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
@Table(name = "tb_cidade")
@AllArgsConstructor
@NoArgsConstructor
public class CidadeModel implements Serializable {


    private static final long serialVersionUID = -3812470179144563231L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "dsc_nome")
    private String nome;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "fk_uf")
    private UfModel uf;
}
