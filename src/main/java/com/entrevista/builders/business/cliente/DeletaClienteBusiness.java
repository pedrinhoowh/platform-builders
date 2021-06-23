package com.entrevista.builders.business.cliente;

import com.entrevista.builders.domain.ClienteDomain;
import com.entrevista.builders.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeletaClienteBusiness {

    @Autowired
    private BuscaClienteBusiness buscaClienteBusiness;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleta(String cpfCnpj) {
        ClienteDomain clienteDomain = buscaClienteBusiness.buscaPorID(cpfCnpj);

        clienteRepository.deleteById(cpfCnpj);
    }

}
