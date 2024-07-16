package br.com.fiap.tech_challenge.application;

import br.com.fiap.tech_challenge.domain.Cliente;
import br.com.fiap.tech_challenge.domain.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/cliente")
public class ClienteController implements ClienteApi {

    private ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public ResponseEntity<String> heathCheck() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente cliente) {
        service.cadastrarCliente(cliente);
        return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Object> buscarClientePorCPF(@PathVariable String cpf) {
        Cliente cliente = service.buscarClientePorCPF(cpf);
        if(cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Object> atualizarCliente(@RequestBody Cliente cliente) {
        try {
            service.atualizarCliente(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar as informações do cliente.", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/id/{id}")
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