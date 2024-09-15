package br.com.fiap.tech_challenge.core.application.usecase;

import br.com.fiap.tech_challenge.core.domain.model.Cliente;

public interface ClienteUseCase {
    void cadastrarCliente(Cliente cliente);
    Cliente buscarClientePorCPF(String cpf);
    void atualizarCliente(Cliente cliente);
    String excluirCliente(Long id);
}
