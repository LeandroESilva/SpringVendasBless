package bless.leandro.Vendas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"bless.leandro.Vendas.repository",
		"bless.leandro.Vendas.service.MyServices"})
@RestController
public class VendasApplication {

	@Value("${application.name}")
	private String application;

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);

	}
	@GetMapping("/hello")
	public String helloworld(){
		return "hello world";
	}

}
