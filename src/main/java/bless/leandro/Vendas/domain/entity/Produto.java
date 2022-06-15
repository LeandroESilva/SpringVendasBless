package bless.leandro.Vendas.domain.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Produto {
    private  Integer id;
    private  String descricao;
    private BigDecimal precoUnitario;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
