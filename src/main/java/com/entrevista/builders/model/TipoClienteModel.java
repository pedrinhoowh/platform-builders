package com.entrevista.builders.model;

import com.entrevista.builders.enums.TipoClienteEnum;
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
@Table(name = "tb_tipo_cliente")
@AllArgsConstructor
@NoArgsConstructor
public class TipoClienteModel implements Serializable {


    private static final long serialVersionUID = 387663683703274706L;
    @Id
    @Size(max = 2)
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente")
    private TipoClienteEnum tipoCliente;

    @NotNull
    @Size(max = 50)
    @Column(name = "dsc_tipo_cliente")
    private String descricao;

}
