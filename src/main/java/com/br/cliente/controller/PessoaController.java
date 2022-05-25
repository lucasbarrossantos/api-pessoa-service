package com.br.cliente.controller;

import com.br.cliente.controller.spec.PessoaControllerSpec;
import com.br.cliente.model.Pessoa;
import com.br.cliente.model.dto.PessoaDto;
import com.br.cliente.service.PessoaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/pessoas")
public class PessoaController implements PessoaControllerSpec {

    @Autowired
    private PessoaService pessoaService;

    @Override
    @PostMapping
    public ResponseEntity<Pessoa> salvarPessoa(@RequestBody @Valid PessoaDto pessoaDto) {
        return ResponseEntity.ok(pessoaService.salvar(pessoaDto));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<Pessoa>> getPessoas(Pageable page) {
        return ResponseEntity.ok(pessoaService.buscarPessoas(page));
    }

    @Override
    @GetMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> getPessoaPorId(@PathVariable Long pessoaId) {
        return ResponseEntity.ok(pessoaService.buscarOuFalhar(pessoaId));
    }

    @Override
    @PutMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> atualizar(@RequestBody PessoaDto pessoaDto, @PathVariable Long pessoaId) {
        return ResponseEntity.ok(pessoaService.atualizar(pessoaDto, pessoaId));
    }

    @Override
    @DeleteMapping("/{pessoaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long pessoaId) {
        pessoaService.excluir(pessoaId);
    }

}
