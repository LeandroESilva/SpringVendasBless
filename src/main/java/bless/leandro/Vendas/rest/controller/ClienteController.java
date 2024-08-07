package bless.leandro.Vendas.rest.controller;

import bless.leandro.Vendas.domain.entity.Cliente;
import bless.leandro.Vendas.domain.repository.Clientes;
import org.springframework.data .domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes){
        this.clientes = clientes;
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);

        if(cliente.isPresent()){
            return cliente.get();
        }
        throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado!");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente){
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        clientes.findById(id).map(cliente -> {
            clientes.delete(cliente);
            return Void.TYPE;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,@RequestBody Cliente cliente){
        clientes.findById(id).map(clienteExistente ->{
            cliente.setId(clienteExistente.getId());
            clientes.save(cliente);
            return clienteExistente;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro){
        //Pesquisa dinamicamente pelo filtro passado pelo parametro
        ExampleMatcher matcher = ExampleMatcher
                .matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }
}
