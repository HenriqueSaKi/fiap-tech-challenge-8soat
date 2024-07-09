package br.com.fiap.tech_challenge.domain.services;

import br.com.fiap.tech_challenge.domain.Cliente;

public interface ClienteService {

    void save(Cliente cliente);
    Cliente findById(String cpf);
    void deleteById(String cpf);

}
