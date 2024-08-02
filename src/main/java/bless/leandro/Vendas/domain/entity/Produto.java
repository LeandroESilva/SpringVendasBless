package bless.leandro.Vendas.domain.entity;

import lombok.*;
import org.aspectj.bridge.Message;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private  String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal preco;

}

