package bless.leandro.Vendas.domain.repository;

import bless.leandro.Vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
