package bless.leandro.Vendas.rest.controller;

import bless.leandro.Vendas.domain.entity.Cliente;
import bless.leandro.Vendas.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteControllerExemplo1 {

    private Clientes clientes;

    public ClienteControllerExemplo1(Clientes clientes){
        this.clientes = clientes;
    }

    @GetMapping("/buscar/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);

        if(cliente.isPresent()){
            ResponseEntity<Cliente> responseEntity = new ResponseEntity<>(cliente.get(), HttpStatus.OK);
            return ResponseEntity.ok(cliente.get());
        }
        return  ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
        Cliente resultado = clientes.save(cliente);
        return  ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);

        if(cliente.isPresent()){
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar/{id}")
    @ResponseBody
    public ResponseEntity updatfe(@PathVariable Integer id,@RequestBody Cliente cliente){
        return  clientes.findById(id).map(clienteExistente ->{
            cliente.setId(clienteExistente.getId());
            clientes.save(cliente);
            return  ResponseEntity.noContent().build();
        }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity find(Cliente filtro){
        //Pesquisa dinamicamente pelo filtro passado peloparametro
        ExampleMatcher matcher = ExampleMatcher
                .matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<Clientes> lista = clientes.findAll(example);
        return ResponseEntity.ok(lista);
    }
}
