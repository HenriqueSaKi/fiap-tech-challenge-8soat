package br.com.fiap.tech_challenge.adapters.driver.controller;

import br.com.fiap.tech_challenge.adapters.driver.controller.swagger.ClienteSwaggerInterface;
import br.com.fiap.tech_challenge.core.domain.model.ClienteDTO;
import br.com.fiap.tech_challenge.core.domain.ports.in.ClienteServicePort;
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

    private final ClienteServicePort service;

    @Autowired
    public ClienteController(ClienteServicePort service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<String> heathCheck() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            service.cadastrarCliente(clienteDTO);
            return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("Cliente já existente!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> buscarClientePorCPF(@PathVariable String cpf) {
        ClienteDTO clienteDTO = service.buscarClientePorCPF(cpf);
        if(clienteDTO != null) {
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> atualizarCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            service.atualizarCliente(clienteDTO);
            return new ResponseEntity<>(clienteDTO, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar as informações do cliente.", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<String> excluirCliente (@PathVariable Long id) {
        if(service.existeCliente(id)) {
            service.excluirCliente(id);
            return new ResponseEntity<>("Cliente excluído com sucesso!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Cliente não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

}
