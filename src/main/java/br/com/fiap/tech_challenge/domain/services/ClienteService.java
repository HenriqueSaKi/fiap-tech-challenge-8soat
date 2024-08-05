package br.com.fiap.tech_challenge.domain.services;

import br.com.fiap.tech_challenge.domain.ClienteDTO;

public interface ClienteService {
    void cadastrarCliente(ClienteDTO clienteDTO);
    ClienteDTO buscarClientePorCPF(String cpf);
    void atualizarCliente(ClienteDTO clienteDTO);
    boolean existeCliente(Long id);
    void excluirCliente(Long id);

}
