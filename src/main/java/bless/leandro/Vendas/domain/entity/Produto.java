package bless.leandro.Vendas.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

//lombok
// @Data // coopilado de metodos contrutor, get, set, equal e hashcode, tostring...
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Integer id;

    @Column(name = "descricao")
    private  String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal preco;

}

