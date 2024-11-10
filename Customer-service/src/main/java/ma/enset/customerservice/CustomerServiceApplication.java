package ma.enset.customerservice;

import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
   public CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration){
      repositoryRestConfiguration.exposeIdsFor(Customer.class);
       return args -> {
           customerRepository.save(new Customer(null,"khadija","khadija@gmail.com"));
           customerRepository.save(new Customer(null,"hamza","hamza@gmail.com"));
           customerRepository.save(new Customer(null,"imane","imane@gmail.com"));
       };
    }
}
