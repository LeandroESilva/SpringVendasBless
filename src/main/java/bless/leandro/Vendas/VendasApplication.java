package bless.leandro.Vendas;

import bless.leandro.Vendas.domain.entity.Cliente;
import bless.leandro.Vendas.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class }) //Excluir configuração padrão do security
public class VendasApplication {
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes){
		return  args -> {
			clientes.salvar( new Cliente("Alicia"));
			clientes.salvar( new Cliente("Andrezza"));

			//List<Cliente> buscarClientes = clientes.buscarRegistros();
			//buscarClientes.forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}


}
