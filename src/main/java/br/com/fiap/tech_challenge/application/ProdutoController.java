package br.com.fiap.tech_challenge.application;

import br.com.fiap.tech_challenge.domain.ProdutoDTO;
import br.com.fiap.tech_challenge.domain.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.infra.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.domain.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService produtoServicePort) {
        this.service = produtoServicePort;
    }

//    @PostMapping
//    public ResponseEntity<ProdutoEntity> criarProdutoEntity(@Valid @RequestBody ProdutoDTO produtoDTO) {
//        var produtoModel = new ProdutoEntity();
//
//        BeanUtils.copyProperties(produtoDTO, produtoModel);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(produtoModel));
//    }
//
//    @GetMapping("/{ProdutoEntityId}")
//    public ResponseEntity<Object> findProdutoEntityById(@PathVariable(value = "ProdutoEntityId") UUID produtoEntityId) {
//        Optional<ProdutoEntity> produto = service.findById(produtoEntityId);
//
//        if (produto.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoEntity não encontrado");
//        } else {
//            return ResponseEntity.ok().body(produto);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<Object> findAllProdutos() {
//        List<ProdutoEntity> produtoList = service.findAll();
//
//        if (produtoList.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há produtos registrados.");
//        } else {
//            return ResponseEntity.status(HttpStatus.OK).body(produtoList);
//        }
//    }
//
//    @DeleteMapping("/{produtoId}")
//    public ResponseEntity<Object> deleteProdutoEntityById(@PathVariable(value = "produtoId") UUID produtoId) {
//        Optional<ProdutoEntity> produtoEntity = service.findById(produtoId);
//
//        if (produtoEntity.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
//        } else {
//            service.delete(produtoEntity.get());
//            return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
//        }
//    }

    @GetMapping("/produto/{categoria}")
    public ResponseEntity<Object> buscarPorCategoria(@PathVariable (value = "categoria") CategoriaProduto categoria){
        List<ProdutoEntity> produtoList = service.buscarPorCategoria(categoria);

        if (produtoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há produtos regitrados");
        } else{
            return ResponseEntity.ok().body(produtoList);
        }
    }
}