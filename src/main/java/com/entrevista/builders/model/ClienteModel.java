package com.entrevista.builders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_cliente")
@AllArgsConstructor
@NoArgsConstructor
public class ClienteModel implements Serializable {

    private static final long serialVersionUID = -4329644525211720779L;

    @Id
    @NotNull
    @Size(max = 14)
    private String cpfCnpj;

    @NotNull
    @Size(max = 50)
    @Column(name = "dsc_nome")
    private String nome;

    @NotNull
    @Size(max = 50)
    @Column(name = "dsc_email")
    private String email;

    @NotNull
    @Column(name = "dat_nascimento")
    private LocalDate dataNascimento;

    @Size(max = 50)
    @Column(name = "dsc_alcunha")
    private String alcunha;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "fk_tipo_cliente")
    private TipoClienteModel tipoCliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "fk_endereco")
    private EnderecoModel endereco;




}
