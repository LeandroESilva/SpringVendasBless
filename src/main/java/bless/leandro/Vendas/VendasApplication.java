package bless.leandro.Vendas;

import bless.leandro.Vendas.domain.entity.Cliente;
import bless.leandro.Vendas.domain.entity.Pedido;
import bless.leandro.Vendas.domain.repository.Clientes;
import bless.leandro.Vendas.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class }) //Excluir configuração padrão do security
public class VendasApplication {
	@Bean
	public CommandLineRunner init(
			@Autowired Clientes clientes,
			@Autowired Pedidos pedidos){
		return  args -> {
			Cliente fulano = new Cliente("Alicia");
			//clientes.save( new Cliente("Andrezza"));
			clientes.save(fulano);

			Pedido p = new Pedido();
			p.setCliente(fulano);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100.00));
			pedidos.save(p);

			Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
			System.out.println(cliente);
			System.out.println(cliente.getPedidos());

			pedidos.findByCliente(fulano).forEach(System.out::println);


			/*List<Cliente> buscarClientes = clientes.findAll();
			buscarClientes.forEach(System.out::println);

			System.out.println("Ataulizar clientes");
			buscarClientes.forEach(c -> {
				c.setNome(c.getNome() + "Atualizado!");
				clientes.save(c); });

			System.out.println("Buscando cliente");
			clientes.findByNomeLike("Al").forEach(System.out::println);

			System.out.println("Deletar clientes");
			//clientes.deleteAll();
			clientes.findAll().forEach(cliente -> {
				clientes.delete(cliente);
			});

			buscarClientes = clientes.findAll();
			buscarClientes.forEach(System.out::println);
			System.out.println("N de Clientes cadastrados: "+ buscarClientes.size());*/
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
