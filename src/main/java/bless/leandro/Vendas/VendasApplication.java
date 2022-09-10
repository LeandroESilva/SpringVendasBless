package bless.leandro.Vendas;

import bless.leandro.Vendas.domain.entity.Cliente;
import bless.leandro.Vendas.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class }) //Excluir configuração padrão do security
public class VendasApplication {

	/*@Bean
	public CommandLineRunner commandLineRunner(@Autowired Clientes clientes){
		return  args -> {
			Cliente c = new Cliente("Andrezza");
			clientes.save(c);
		};
	};*/


	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
