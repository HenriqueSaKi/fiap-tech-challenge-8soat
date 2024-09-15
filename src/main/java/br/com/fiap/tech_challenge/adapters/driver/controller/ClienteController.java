package br.com.fiap.tech_challenge.adapters.driver.controller;

import br.com.fiap.tech_challenge.adapters.driver.controller.mapper.ClienteDTOMapper;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarClienteDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarClienteDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.swagger.ClienteSwaggerInterface;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.application.usecase.ClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/cliente")
public class ClienteController implements ClienteSwaggerInterface {

    private final ClienteUseCase clienteUseCase;
    private final ClienteDTOMapper clienteDTOMapper;

    @Autowired
    public ClienteController(ClienteUseCase clienteUseCase, ClienteDTOMapper clienteDTOMapper) {
        this.clienteUseCase = clienteUseCase;
        this.clienteDTOMapper = clienteDTOMapper;
    }

    @Override
    public ResponseEntity<String> cadastrarCliente(@RequestBody CadastrarClienteDTO cadastrar) {
        Cliente cliente = clienteDTOMapper.cadastrarToCliente(cadastrar);
        clienteUseCase.cadastrarCliente(cliente);
        return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Cliente> buscarClientePorCPF(@PathVariable String cpf) {
        Cliente cliente = clienteUseCase.buscarClientePorCPF(cpf);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody AtualizarClienteDTO atualizar) {
        Cliente cliente = clienteDTOMapper.atualizarToCliente(atualizar);
        clienteUseCase.atualizarCliente(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> excluirCliente (@PathVariable Long id) {
        return new ResponseEntity<>(clienteUseCase.excluirCliente(id), HttpStatus.OK);
    }

}
