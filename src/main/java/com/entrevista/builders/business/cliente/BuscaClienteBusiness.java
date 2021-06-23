package com.entrevista.builders.business.cliente;

import com.entrevista.builders.domain.ClienteDomain;
import com.entrevista.builders.enums.TipoClienteEnum;
import com.entrevista.builders.exception.unchecked.NegocioException;
import com.entrevista.builders.exception.unchecked.ObjetoNaoEncontrado;
import com.entrevista.builders.mapper.ClienteMapper;
import com.entrevista.builders.model.ClienteModel;
import com.entrevista.builders.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BuscaClienteBusiness {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Transactional(readOnly = true)
    public ClienteDomain buscaPorID(String cpfCnpj) {
        Optional<ClienteModel> clienteModelOptional = clienteRepository.findById(cpfCnpj);

        ClienteModel clienteModel = clienteModelOptional.orElseThrow(() -> new ObjetoNaoEncontrado("Não foi possível encontrar um cliente com o ID requirido para atualizar"));

        return clienteMapper.toDomain(clienteModel);
    }

    @Transactional(readOnly = true)
    public Page<ClienteDomain> buscaPaginada(TipoClienteEnum tipoClienteEnum, String siglaEstado,
                                             String cidade, Integer page, Integer size) {

        if(size > 100) {
            throw new NegocioException("Não é possível realizar uma busca paginada com mais de 100 registros por página!");
        }

        Pageable paginacao = PageRequest.of(page, size);

        Page<ClienteModel> paginada = clienteRepository.findAll(tipoClienteEnum, siglaEstado, cidade, paginacao);

        List<ClienteDomain> dominios = CollectionUtils.isEmpty(paginada.getContent()) ? new ArrayList<>()
                : paginada
                .getContent()
                .stream()
                .map(clienteMapper::toDomain)
                .collect(Collectors.toList());

        return new PageImpl<>(dominios, paginacao, paginada.getTotalElements());
    }

}
