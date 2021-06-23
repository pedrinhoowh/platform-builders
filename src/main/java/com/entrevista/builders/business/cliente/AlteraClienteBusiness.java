package com.entrevista.builders.business.cliente;

import com.entrevista.builders.business.cidade.BuscaCidadeBusiness;
import com.entrevista.builders.domain.CidadeDomain;
import com.entrevista.builders.domain.ClienteDomain;
import com.entrevista.builders.exception.checked.CidadeNaoEncontradaException;
import com.entrevista.builders.exception.unchecked.ObjetoNaoEncontrado;
import com.entrevista.builders.mapper.ClienteMapper;
import com.entrevista.builders.repository.ClienteRepository;
import com.entrevista.builders.validator.PersisteClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.entrevista.builders.constants.ComumConstants.CIDADE_NAO_ENCONTRADA;

@Component
public class AlteraClienteBusiness {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BuscaCidadeBusiness buscaCidadeBusiness;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private BuscaClienteBusiness buscaClienteBusiness;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ClienteDomain altera(Map<String, Object> cliente, String cpfCnpj) {
        try {

            ClienteDomain clienteDomain = buscaClienteBusiness.buscaPorID(cpfCnpj);

            clienteDomain.alteraCliente(cliente, clienteDomain);

            PersisteClienteValidator.validaCriacao(clienteDomain);

            CidadeDomain cidadeDomain = buscaCidadeBusiness.buscaCidade(clienteDomain.getEndereco().getCidade().getNome(),
                    clienteDomain.getEndereco().getCidade().getUf().getSigla());

            clienteDomain.atualizaCidade(cidadeDomain);

            return clienteMapper.toDomain(clienteRepository.save(clienteMapper.toModel(clienteDomain)));

        } catch (CidadeNaoEncontradaException e) {
            throw new ObjetoNaoEncontrado(CIDADE_NAO_ENCONTRADA);
        }

    }

}
