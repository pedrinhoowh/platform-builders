package com.entrevista.builders.controller;

import com.entrevista.builders.business.cliente.AlteraClienteBusiness;
import com.entrevista.builders.business.cliente.BuscaClienteBusiness;
import com.entrevista.builders.business.cliente.CriaClienteBusiness;
import com.entrevista.builders.business.cliente.DeletaClienteBusiness;
import com.entrevista.builders.domain.ClienteDomain;
import com.entrevista.builders.enums.TipoClienteEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private CriaClienteBusiness criaClienteBusiness;

    @Autowired
    private AlteraClienteBusiness alteraClienteBusiness;

    @Autowired
    private BuscaClienteBusiness buscaClienteBusiness;

    @Autowired
    private DeletaClienteBusiness deletaClienteBusiness;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDomain> cria(@RequestBody ClienteDomain clienteDomain) {
        return ResponseEntity
                .created(URI.create("/api/cliente"))
                .body(criaClienteBusiness.persiste(clienteDomain));
    }

    @PatchMapping(value = "/{cpfCnpj}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDomain> altera(@RequestBody Map<String, Object> valores, @PathVariable(value = "cpfCnpj") String cpfCnpj) {
        return ResponseEntity.ok(alteraClienteBusiness.altera(valores, cpfCnpj));
    }

    @GetMapping(value = "/{cpfCnpj}")
    public ResponseEntity<ClienteDomain> buscaPorId(@PathVariable(value = "cpfCnpj") String cpfCnpj) {
        return ResponseEntity.ok(buscaClienteBusiness.buscaPorID(cpfCnpj));
    }

    @DeleteMapping(value = "/{cpfCnpj}")
    public ResponseEntity<?> delete(@PathVariable(value = "cpfCnpj") String cpfCnpj) {
        deletaClienteBusiness.deleta(cpfCnpj);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDomain>> buscaPaginada(@RequestParam(value = "tipoCliente", required = false)TipoClienteEnum tipoClienteEnum,
                                                             @RequestParam(value = "siglaEstado", required = false) String siglaEstado,
                                                             @RequestParam(value = "cidade", required = false) String cidade,
                                                             @RequestParam(value = "page") Integer page,
                                                             @RequestParam(value = "size") Integer size) {

        return ResponseEntity.ok(buscaClienteBusiness.buscaPaginada(tipoClienteEnum, siglaEstado, cidade, page, size));
    }

}
