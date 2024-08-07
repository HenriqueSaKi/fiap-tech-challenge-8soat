package br.com.fiap.tech_challenge.core.domain.ports.in;

import br.com.fiap.tech_challenge.core.domain.model.ClienteDTO;

public interface ClienteServicePort {
    void cadastrarCliente(ClienteDTO clienteDTO);
    ClienteDTO buscarClientePorCPF(String cpf);
    void atualizarCliente(ClienteDTO clienteDTO);
    boolean existeCliente(Long id);
    void excluirCliente(Long id);

}
