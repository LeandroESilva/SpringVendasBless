package bless.leandro.Vendas.service.imp;

import bless.leandro.Vendas.domain.entity.Cliente;
import bless.leandro.Vendas.domain.entity.Pedido;
import bless.leandro.Vendas.domain.repository.Clientes;
import bless.leandro.Vendas.domain.repository.Pedidos;
import bless.leandro.Vendas.domain.repository.Produtos;
import bless.leandro.Vendas.exception.RegraNegocioException;
import bless.leandro.Vendas.rest.dto.ItemPedidoDTO;
import bless.leandro.Vendas.rest.dto.PedidoDTO;
import bless.leandro.Vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor //Lombak
@Service
public class PedidoServiceImp implements PedidoService {

    private final Pedidos pedidos;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;

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

        return null;
    }

    private void salvarItens(List<ItemPedidoDTO> itens){

    }

}
