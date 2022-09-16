package bless.leandro.Vendas.rest.dto;
//lombok
// @Data // coopilado de metodos contrutor, get, set, equal e hashcode, tostring...

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

//lombok
// @Data // coopilado de metodos contrutor, get, set, equal e hashcode, tostring...
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;


}
