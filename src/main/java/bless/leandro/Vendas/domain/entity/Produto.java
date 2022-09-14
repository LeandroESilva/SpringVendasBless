package bless.leandro.Vendas.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() { return id; }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}

