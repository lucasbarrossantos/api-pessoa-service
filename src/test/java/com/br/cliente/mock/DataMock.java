package com.br.cliente.mock;

import com.br.cliente.model.Pessoa;
import com.br.cliente.model.dto.PessoaDto;
import org.springframework.stereotype.Component;

import java.util.Date;

public class DataMock {

    public Pessoa getPessoa() {
        return Pessoa.builder()
                .id(1L)
                .nome("Lucas Barros Santos")
                .cpf("111.222.333.44")
                .dataNascimento(new Date(1992, 8, 14))
                .rg("1234567")
                .idade(29)
                .build();
    }

    public PessoaDto getPessoaDto() {
        return PessoaDto.builder()
                .nome("Lucas Barros Santos")
                .cpf("111.222.333.44")
                .dataNascimento(new Date(1992, 8, 14))
                .rg("1234567")
                .idade(29)
                .build();
    }

}
