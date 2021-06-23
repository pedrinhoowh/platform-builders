package com.entrevista.builders.business.cliente;

import com.entrevista.builders.business.cidade.BuscaCidadeBusiness;
import com.entrevista.builders.domain.CidadeDomain;
import com.entrevista.builders.domain.ClienteDomain;
import com.entrevista.builders.exception.checked.CidadeNaoEncontradaException;
import com.entrevista.builders.exception.unchecked.NegocioException;
import com.entrevista.builders.exception.unchecked.ObjetoNaoEncontrado;
import com.entrevista.builders.mapper.ClienteMapper;
import com.entrevista.builders.model.ClienteModel;
import com.entrevista.builders.repository.ClienteRepository;
import com.entrevista.builders.validator.PersisteClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.entrevista.builders.constants.ComumConstants.CIDADE_NAO_ENCONTRADA;

@Component
public class CriaClienteBusiness {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BuscaCidadeBusiness buscaCidadeBusiness;

    @Autowired
    private ClienteMapper clienteMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ClienteDomain persiste(ClienteDomain cliente) {
        try {

            PersisteClienteValidator.validaCriacao(cliente);

            Optional<ClienteModel> clienteBanco = clienteRepository.findById(cliente.getCpfCnpj());

            if (clienteBanco.isPresent())
                throw new NegocioException("Já existe um cliente com cpfCnpj informado!");

            CidadeDomain cidadeDomain = buscaCidadeBusiness.buscaCidade(cliente.getEndereco().getCidade().getNome(),
                    cliente.getEndereco().getCidade().getUf().getSigla());

            cliente.atualizaCidade(cidadeDomain);

            ClienteModel clienteModel = clienteRepository.save(clienteMapper.toModel(cliente));

            return clienteMapper.toDomain(clienteModel);

        } catch (CidadeNaoEncontradaException e) {
            throw new ObjetoNaoEncontrado(CIDADE_NAO_ENCONTRADA);
        }
    }

}
