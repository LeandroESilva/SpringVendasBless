package bless.leandro.Vendas.service;

import bless.leandro.Vendas.domain.entity.Pedido;
import bless.leandro.Vendas.domain.enums.StatusPedido;
import bless.leandro.Vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService{

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
