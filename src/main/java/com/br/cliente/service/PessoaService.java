package com.br.cliente.service;

import com.br.cliente.model.Pessoa;
import com.br.cliente.model.dto.PessoaDto;
import com.br.cliente.repository.PessoaRepository;
import com.br.cliente.service.exceptions.PessoaNotFoundException;
import com.br.cliente.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MapperUtil mapperUtil;

    public Pessoa salvar(PessoaDto pessoaDTO) {
        return pessoaRepository.save(mapperUtil.convertTo(pessoaDTO, Pessoa.class));
    }

    public Page<Pessoa> buscarPessoas(Pageable page) {
        return pessoaRepository.findAll(page);
    }

    public Pessoa atualizar(PessoaDto pessoaDto, Long pessoaId) {
        Pessoa pessoa = buscarOuFalhar(pessoaId);
        mapperUtil.copyProperties(pessoaDto, pessoa, "id");
        return pessoaRepository.save(pessoa);
    }

    public void excluir(Long pessoaId) {
        Pessoa pessoa = buscarOuFalhar(pessoaId);
        pessoaRepository.delete(pessoa);
    }

    public Pessoa buscarOuFalhar(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException(id));
    }

}
