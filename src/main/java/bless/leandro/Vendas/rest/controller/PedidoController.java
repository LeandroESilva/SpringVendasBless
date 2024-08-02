package bless.leandro.Vendas.rest.controller;

import bless.leandro.Vendas.domain.entity.ItemPedido;
import bless.leandro.Vendas.domain.entity.Pedido;
import bless.leandro.Vendas.domain.enums.StatusPedido;
import bless.leandro.Vendas.rest.dto.AtualizacaoStatusPedidoDTO;
import bless.leandro.Vendas.rest.dto.InformacoesItemPedidoDTO;
import bless.leandro.Vendas.rest.dto.InformacoesPedidoDTO;
import bless.leandro.Vendas.rest.dto.PedidoDTO;
import bless.leandro.Vendas.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return  service.obterPedidoCompleto(id).map( p -> converterPedido(p))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
    }


    @PatchMapping("{id}") //É um update que atualiza o objeto de forma parcial.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable @Valid Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO converterPedido(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converterItensPedido(pedido.getItens()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converterItensPedido(List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(item -> InformacoesItemPedidoDTO.builder()
                .descricaoProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco())
                .quantidade(item.getQuantidade())
                .build()
        ).collect(Collectors.toList());
    }
}
