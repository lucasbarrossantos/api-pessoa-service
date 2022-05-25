package com.br.cliente.service;

import com.br.cliente.mock.DataMock;
import com.br.cliente.model.Pessoa;
import com.br.cliente.repository.PessoaRepository;
import com.br.cliente.service.exceptions.PessoaNotFoundException;
import com.br.cliente.util.MapperUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private MapperUtil mapperUtil;

    private final DataMock mock = new DataMock();

    @DisplayName("Deve salvar uma Pessoa com sucesso")
    @Test
    void salvarPessoaComSucesso() {

        var pessoa = mock.getPessoa();
        var pessoaDto = mock.getPessoaDto();

        Mockito.when(pessoaRepository.save(Mockito.any(Pessoa.class)))
                .thenReturn(pessoa);

        Mockito.when(mapperUtil.convertTo(pessoaDto, Pessoa.class))
                .thenReturn(pessoa);

        Pessoa result = pessoaService.salvar(pessoaDto);

        assertNotNull(result);
        assertEquals(pessoa.getCpf(), pessoaDto.getCpf());
        Mockito.verify(pessoaRepository, Mockito.atLeastOnce()).save(pessoa);
    }

    @DisplayName("Deve buscar uma Pessoa com sucesso")
    @Test
    void buscarClienteComSucesso() {
        var entity = mock.getPessoa();

        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(entity));

        Pessoa pessoa = pessoaService.buscarOuFalhar(1L);

        assertNotNull(pessoa);
        assertEquals(pessoa.getIdade(), entity.getIdade());
        Mockito.verify(pessoaRepository, Mockito.atLeastOnce()).findById(1L);
    }

    @DisplayName("Deve lançar exception PessoaNotFoundException ao tentar buscar pessoa que não existe")
    @Test
    void pesquisarPessoaNaoExistente() {
        var id = 1L;
        Throwable exception = assertThrows(PessoaNotFoundException.class, () -> {
            Mockito.when(pessoaRepository.findById(id)).thenReturn(Optional.empty());
            pessoaService.buscarOuFalhar(id);
        });

        assertEquals(String.format("Não existe uma pessoa com o id: %s na base de dados!", id), exception.getMessage());
    }

    @DisplayName("Deve excluir uma pessoa com sucesso")
    @Test
    void deveExcluirPessoaComSucesso() {
        var id = 1L;
        var entity = mock.getPessoa();

        Mockito.when(pessoaRepository.findById(id)).thenReturn(Optional.of(entity));
        Mockito.doNothing().when(pessoaRepository).delete(entity);


        pessoaService.excluir(id);

        Mockito.verify(pessoaRepository, Mockito.atLeastOnce()).delete(entity);
    }


}
