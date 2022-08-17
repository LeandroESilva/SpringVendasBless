package bless.leandro.Vendas.domain.repository;

import bless.leandro.Vendas.domain.entity.Cliente;
import bless.leandro.Vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
