package br.com.fiap.tech_challenge.application;

import br.com.fiap.tech_challenge.application.swagger.ClienteSwaggerInterface;
import br.com.fiap.tech_challenge.domain.ClienteDTO;
import br.com.fiap.tech_challenge.domain.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/cliente")
public class ClienteController implements ClienteSwaggerInterface {

    private ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<String> heathCheck() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        service.cadastrarCliente(clienteDTO);
        return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.CREATED);
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
