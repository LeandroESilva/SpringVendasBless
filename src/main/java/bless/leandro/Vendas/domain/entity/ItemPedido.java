package bless.leandro.Vendas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//lombok
// @Data // coopilado de metodos contrutor, get, set, equal e hashcode, tostring...
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "ItemPedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private  Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "quantidade")
    private Integer quantidade;

}
