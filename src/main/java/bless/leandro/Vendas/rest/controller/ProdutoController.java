package bless.leandro.Vendas.rest.controller;

import bless.leandro.Vendas.domain.entity.Produto;
import bless.leandro.Vendas.domain.repository.Produtos;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos produtos;

    public ProdutoController(Produtos produtos){this.produtos = produtos;}

    @RequestMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id){
        return produtos.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

}
