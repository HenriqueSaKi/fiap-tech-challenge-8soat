package br.com.fiap.tech_challenge.core.domain.mock;

import br.com.fiap.tech_challenge.core.domain.model.EnderecoDTO;

public class EnderecoMock {

    public static EnderecoDTO getEndereco() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep("12345-123");
        enderecoDTO.setLogradouro("Rua Endereco Teste");
        enderecoDTO.setLogradouro("Teste");
        enderecoDTO.setBairro("Bairro Teste");
        enderecoDTO.setCidade("Cidade Teste");
        enderecoDTO.setEstado("Estado Teste");
        return enderecoDTO;
    }

}
