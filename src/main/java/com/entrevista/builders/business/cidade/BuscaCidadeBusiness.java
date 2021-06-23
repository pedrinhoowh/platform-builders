package com.entrevista.builders.business.cidade;

import com.entrevista.builders.domain.CidadeDomain;
import com.entrevista.builders.exception.checked.CidadeNaoEncontradaException;
import com.entrevista.builders.mapper.CidadeMapper;
import com.entrevista.builders.model.CidadeModel;
import com.entrevista.builders.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class BuscaCidadeBusiness {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeMapper cidadeMapper;

    @Transactional(readOnly = true)
    public CidadeDomain buscaCidade(String nome, String siglaUf) throws CidadeNaoEncontradaException {
        Optional<CidadeModel> cidadeModelOpt = cidadeRepository.findByNomeAndUf_Sigla(nome, siglaUf);

        CidadeDomain cidadeDomain = cidadeMapper.toDomain(cidadeModelOpt
                .orElseThrow(() -> new CidadeNaoEncontradaException("Cidade não encontrada!")));

        return cidadeDomain;
    }

}
