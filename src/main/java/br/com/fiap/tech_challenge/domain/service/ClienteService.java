package br.com.fiap.tech_challenge.domain.service;

import br.com.fiap.tech_challenge.domain.Cliente;

public interface ClienteService {
    void cadastrarCliente(Cliente cliente);
    Cliente buscarClientePorCPF(String cpf);
    void atualizarCliente(Cliente cliente);
    boolean existeCliente(Long id);
    void excluirCliente(Long id);

}
