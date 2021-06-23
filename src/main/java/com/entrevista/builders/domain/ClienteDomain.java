package com.entrevista.builders.domain;

import com.entrevista.builders.enums.TipoClienteEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDomain implements Serializable {

    private static final long serialVersionUID = 2804657499246676207L;
    private String cpfCnpj;

    private String nome;

    private String email;

    private LocalDate dataNascimento;

    private String alcunha;

    private TipoClienteEnum tipoCliente;

    private EnderecoDomain endereco;

    public void atualizaCidade(CidadeDomain cidadeDomain) {
        this.endereco.setCidade(cidadeDomain);
    }

    public void alteraCliente(Map<String, Object> cliente, ClienteDomain clienteDomain) {
        cliente.remove("cpfCnpj");

        cliente.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(ClienteDomain.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, clienteDomain, v);
        });
    }

}
