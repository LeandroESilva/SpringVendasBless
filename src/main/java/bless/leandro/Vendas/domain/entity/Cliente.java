package bless.leandro.Vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

//lombok
// @Data // coopilado de metodos contrutor, get, set, equal e hashcode, tostring...
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Cliente")
public class Cliente{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Campo cliente é obrigatório!")
    private  String nome;

    @Column(name = "cpf",length = 11)
    @NotEmpty(message = "{campo.CPF.obrigatorio}")
    @CPF(message = "{campo.CPF.invalido}")
    private String cpf;

    @JsonIgnore
    @OneToMany (mappedBy = "cliente", fetch = FetchType.LAZY) //Set = Colection = List
    private Set<Pedido> pedidos;

}
