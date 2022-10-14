package bless.leandro.Vendas.rest.dto;
//lombok
// @Data // coopilado de metodos contrutor, get, set, equal e hashcode, tostring...

import bless.leandro.Vendas.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

//lombok
// @Data // coopilado de metodos contrutor, get, set, equal e hashcode, tostring...
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;
    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;
    @NotEmptyList(message = "{campo.itens-pedido.obrigatorio}")
    private List<ItemPedidoDTO> itens;


}
