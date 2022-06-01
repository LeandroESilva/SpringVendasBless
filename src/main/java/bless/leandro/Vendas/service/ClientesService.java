package bless.leandro.Vendas.service;

import bless.leandro.Vendas.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired //Gerencia a instancia da classe ClientesRepository
    private ClientesRepository repository;


    public void salvarCliente(Object cliente){
        validarCliente(cliente);
        repository.persistir(cliente);
    }

    public void validarCliente(Object cliente){
        //Aplicar validações
    }
}
