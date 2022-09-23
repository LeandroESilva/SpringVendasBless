package bless.leandro.Vendas.service;

import bless.leandro.Vendas.domain.entity.Pedido;
import bless.leandro.Vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService{

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
