package com.entrevista.builders.validator;

import com.entrevista.builders.domain.ClienteDomain;

public final class PersisteClienteValidator {

    public static void validaCriacao(ClienteDomain cliente) {
        ValidadorGenerico validador = ValidadorGenerico.of();

        validador.naoNulo().tamanhoMaximo(14).confirma("cpfCnpj", cliente.getCpfCnpj());
        validador.naoNulo().tamanhoMaximo(50).confirma("nome", cliente.getNome());
        validador.naoNulo().tamanhoMaximo(50).confirma("email", cliente.getEmail());
        validador.naoNulo().confirma("dataNascimento", cliente.getDataNascimento());
        validador.tamanhoMaximo(50).confirma("alcunha", cliente.getAlcunha());
        validador.naoNulo().tamanhoMaximo(2).confirma("tipoCliente", cliente.getTipoCliente());
        validador.naoNulo().confirma("endereco", cliente.getEndereco());

        validador.finaliza();

        validador.naoNulo().tamanhoMaximo(50).confirma("rua", cliente.getEndereco().getRua());
        validador.naoNulo().tamanhoMaximo(999999).confirma("numero", cliente.getEndereco().getNumero());
        validador.naoNulo().tamanhoMaximo(30).confirma("bairro", cliente.getEndereco().getBairro());
        validador.naoNulo().tamanhoMaximo(99999999).confirma("cep", cliente.getEndereco().getCep());
        validador.tamanhoMaximo(100).confirma("complemento", cliente.getEndereco().getComplemento());
        validador.naoNulo().confirma("cidade", cliente.getEndereco().getCidade());

        validador.finaliza();

        validador.naoNulo().confirma("nome_cidade", cliente.getEndereco().getCidade().getNome());
        validador.naoNulo().confirma("uf", cliente.getEndereco().getCidade().getUf());

        validador.finaliza();

        validador.naoNulo().tamanhoMaximo(2).confirma("sigla", cliente.getEndereco().getCidade().getUf().getSigla());

        validador.finaliza();

    }

}
