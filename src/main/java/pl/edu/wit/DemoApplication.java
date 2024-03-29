package pl.edu.wit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaPersistableEntityInformation;
import pl.edu.wit.controller.JaxbFactory;
import pl.edu.wit.jpa.repository.firmaA.customer.AccountRepository;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(AccountRepository repository){
//		return (args) -> {
//			System.out.println("siema z Command Runnera");
//		};
//	}

	@Bean
	public JaxbFactory jaxbFactory(){
		return new JaxbFactory();
	}



}
