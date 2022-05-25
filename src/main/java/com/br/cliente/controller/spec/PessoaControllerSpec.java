package com.br.cliente.controller.spec;

import com.br.cliente.model.Pessoa;
import com.br.cliente.model.dto.PessoaDto;
import com.br.cliente.service.exceptions.EntidadeNaoEncontradaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api(value = "Pessoa Spec", tags = "Pessoa")
public interface PessoaControllerSpec {


    @ApiOperation(value = "Cadastrar nova Pessoa", nickname = "salvarPessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Unexpected Error")
    })
    ResponseEntity<Pessoa> salvarPessoa(@RequestBody @Valid PessoaDto pessoaDto);

    @ApiOperation(value = "Buscar pessoas", nickname = "getPessoas")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Unexpected Error")
    })
    ResponseEntity<Page<Pessoa>> getPessoas(Pageable page);

    @ApiOperation(value = "Buscar pessoa por id", nickname = "getPessoaPorId")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Unexpected Error"),
            @ApiResponse(code = 404, message = "EntityNotFoundException", response = EntidadeNaoEncontradaException.class),
    })
    ResponseEntity<Pessoa> getPessoaPorId(Long pessoaId);

    @ApiOperation(value = "Atualizar pessoa", nickname = "atualizar")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Unexpected Error"),
            @ApiResponse(code = 404, message = "EntityNotFoundException", response = EntidadeNaoEncontradaException.class),
    })
    ResponseEntity<Pessoa> atualizar(@RequestBody PessoaDto pessoaDto, @PathVariable Long pessoaId);

    @ApiOperation(value = "Excluir pessoa", nickname = "excluir")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Unexpected Error"),
            @ApiResponse(code = 204, message = ""),
            @ApiResponse(code = 404, message = "EntityNotFoundException", response = EntidadeNaoEncontradaException.class),
    })
    void excluir(@PathVariable Long pessoaId);
}
