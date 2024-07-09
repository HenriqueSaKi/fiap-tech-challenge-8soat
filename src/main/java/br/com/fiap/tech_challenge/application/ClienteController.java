package br.com.fiap.tech_challenge.application;

import br.com.fiap.tech_challenge.domain.Cliente;
import br.com.fiap.tech_challenge.domain.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/cliente")
public class ClienteController {

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
        service.save(cliente);
        return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarClientePorCPF(@PathVariable String cpf) {
        Cliente cliente = service.findById(cpf);
        if(cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente) {
        try {
            service.save(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> excluirCliente (@PathVariable String cpf) {
        if(service.findById(cpf) != null) {
            service.deleteById(cpf);
            return new ResponseEntity<>("Cliente excluído com sucesso!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Cliente não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

}
