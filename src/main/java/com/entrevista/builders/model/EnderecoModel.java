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
@Table(name = "tb_endereco")
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoModel implements Serializable {


    private static final long serialVersionUID = -1446303905879079565L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "dsc_rua")
    private String rua;

    @NotNull
    @Column(name = "num_endereco")
    private Integer numero;

    @NotNull
    @Size(max = 30)
    @Column(name = "dsc_bairro")
    private String bairro;

    @NotNull
    @Column(name = "num_cep")
    private Integer cep;

    @Size(max = 100)
    @Column(name = "dsc_complemento")
    private String complemento;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_cidade")
    private CidadeModel cidade;

}
