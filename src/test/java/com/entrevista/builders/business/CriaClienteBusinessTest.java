package com.entrevista.builders.business;

import com.entrevista.builders.business.cidade.BuscaCidadeBusiness;
import com.entrevista.builders.business.cliente.CriaClienteBusiness;
import com.entrevista.builders.domain.CidadeDomain;
import com.entrevista.builders.domain.ClienteDomain;
import com.entrevista.builders.domain.EnderecoDomain;
import com.entrevista.builders.domain.UfDomain;
import com.entrevista.builders.enums.TipoClienteEnum;
import com.entrevista.builders.exception.checked.CidadeNaoEncontradaException;
import com.entrevista.builders.exception.unchecked.NegocioException;
import com.entrevista.builders.exception.unchecked.ObjetoInvalidoException;
import com.entrevista.builders.exception.unchecked.ObjetoNaoEncontrado;
import com.entrevista.builders.mapper.ClienteMapper;
import com.entrevista.builders.model.ClienteModel;
import com.entrevista.builders.repository.ClienteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static com.entrevista.builders.constants.ComumConstants.CIDADE_NAO_ENCONTRADA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CriaClienteBusinessTest {

    private static final String CPF = "11111111111";

    private static final String DEFAULT_STRING = "AA";

    private static final Integer DEFAULT_NUMERO = 1344;

    private static final LocalDate DATA_NASCIMENTO = LocalDate.now();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private BuscaCidadeBusiness buscaCidadeBusiness;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private CriaClienteBusiness criaClienteBusiness;

    @Mock
    private ClienteDomain cliente;

    @Mock
    private CidadeDomain cidadeDomain;

    @Before
    public void setup() {
        UfDomain ufDomain = Mockito.mock(UfDomain.class);
        when(ufDomain.getSigla()).thenReturn(DEFAULT_STRING);

        when(this.cidadeDomain.getUf()).thenReturn(ufDomain);
        when(this.cidadeDomain.getNome()).thenReturn(DEFAULT_STRING);

        EnderecoDomain enderecoDomain = Mockito.mock(EnderecoDomain.class);
        when(enderecoDomain.getCidade()).thenReturn(this.cidadeDomain);
        when(enderecoDomain.getBairro()).thenReturn(DEFAULT_STRING);
        when(enderecoDomain.getCep()).thenReturn(DEFAULT_NUMERO);
        when(enderecoDomain.getNumero()).thenReturn(DEFAULT_NUMERO);
        when(enderecoDomain.getRua()).thenReturn(DEFAULT_STRING);


        when(this.cliente.getCpfCnpj()).thenReturn(CPF);
        when(this.cliente.getNome()).thenReturn(DEFAULT_STRING);
        when(this.cliente.getEmail()).thenReturn(DEFAULT_STRING);
        when(this.cliente.getDataNascimento()).thenReturn(DATA_NASCIMENTO);
        when(this.cliente.getTipoCliente()).thenReturn(TipoClienteEnum.PF);
        when(this.cliente.getEndereco()).thenReturn(enderecoDomain);

    }

    @Test
    public void insucesso_clienteExistente() {
        expectedException.expect(NegocioException.class);
        expectedException.expectMessage("Já existe um cliente com cpfCnpj informado!");

        ClienteModel clienteModel = Mockito.mock(ClienteModel.class);

        Optional<ClienteModel> clienteOpt = Optional.of(clienteModel);

        when(clienteRepository.findById(CPF)).thenReturn(clienteOpt);

        criaClienteBusiness.persiste(this.cliente);
    }

    @Test
    public void insucesso_cidadeNaoEncontrada() throws Exception {
        expectedException.expect(ObjetoNaoEncontrado.class);
        expectedException.expectMessage(CIDADE_NAO_ENCONTRADA);

        when(clienteRepository.findById(CPF)).thenReturn(Optional.empty());

        when(buscaCidadeBusiness.buscaCidade(cliente.getEndereco().getCidade().getNome(),
                cliente.getEndereco().getCidade().getUf().getSigla())).thenThrow(CidadeNaoEncontradaException.class);

        criaClienteBusiness.persiste(this.cliente);
    }

    @Test
    public void insucesso_validacaoObrigatoria() {
        expectedException.expect(ObjetoInvalidoException.class);
        expectedException.expectMessage("dataNascimento Campo nulo não permitido!");

        when(this.cliente.getDataNascimento()).thenReturn(null);

        criaClienteBusiness.persiste(this.cliente);
    }

    @Test
    public void sucesso_persistencia() throws Exception {

        when(buscaCidadeBusiness.buscaCidade(DEFAULT_STRING, DEFAULT_STRING)).thenReturn(this.cidadeDomain);

        ClienteModel clienteModel = Mockito.mock(ClienteModel.class);

        when(clienteMapper.toModel(cliente)).thenReturn(clienteModel);
        when(clienteMapper.toDomain(clienteModel)).thenReturn(cliente);

        when(clienteRepository.save(clienteModel)).thenReturn(clienteModel);

        ClienteDomain retorno = criaClienteBusiness.persiste(this.cliente);

        Assert.assertEquals(this.cliente, retorno);
        verify(this.cliente, times(1)).atualizaCidade(this.cidadeDomain);

    }



}


