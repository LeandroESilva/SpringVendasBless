package bless.leandro.Vendas.service.imp;

import bless.leandro.Vendas.domain.entity.Cliente;
import bless.leandro.Vendas.domain.entity.ItemPedido;
import bless.leandro.Vendas.domain.entity.Pedido;
import bless.leandro.Vendas.domain.entity.Produto;
import bless.leandro.Vendas.domain.repository.Clientes;
import bless.leandro.Vendas.domain.repository.ItensPedidos;
import bless.leandro.Vendas.domain.repository.Pedidos;
import bless.leandro.Vendas.domain.repository.Produtos;
import bless.leandro.Vendas.exception.RegraNegocioException;
import bless.leandro.Vendas.rest.dto.ItemPedidoDTO;
import bless.leandro.Vendas.rest.dto.PedidoDTO;
import bless.leandro.Vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor //Lombak
@Service
@Transactional
public class PedidoServiceImp implements PedidoService {

    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;

    private final ItensPedidos itensPedidosRepository;

    /* Sobrescrito pela anotação @RequiredArgsConstructor do Lombok (énecessário adicionar o 'final' nas instancias

    public PedidoServiceImp(Pedidos pedidos, Clientes clientes, Produtos produtos) {
        this.pedidos = pedidos;
        this.clientesRepository = clientes;
        this.produtosRepository = produtos;
    }

     */

    @Override
    public Pedido salvar(PedidoDTO dto) {

        Cliente cliente = clientesRepository.findById(dto.getCliente())
                .orElseThrow(()-> new RegraNegocioException("Código do cliente inválido!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        pedidosRepository.save(pedido);
        itensPedidosRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);

        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw  new RegraNegocioException("O pedido não contem itens!");
        }

        return itens.stream().map(dto -> {
            Produto produto = produtosRepository.findById(dto.getProduto())
                    .orElseThrow(()-> new RegraNegocioException("Produto "+dto.getProduto()+" inexistente!"));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);

            return itemPedido;
        } ).collect(Collectors.toList());
    }

}
