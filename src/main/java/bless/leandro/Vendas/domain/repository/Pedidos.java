package bless.leandro.Vendas.domain.repository;

import bless.leandro.Vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
