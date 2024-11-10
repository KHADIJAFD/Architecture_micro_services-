package ma.enset.inventoryservices;

import ma.enset.inventoryservices.entities.Product;
import ma.enset.inventoryservices.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServicesApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        repositoryRestConfiguration.exposeIdsFor(Product.class);
        return args -> {
            productRepository.save(new Product(null,"laptop",5000,12));
            productRepository.save(new Product(null,"phone",2000,3));
            productRepository.save(new Product(null,"camera",1500,8));
        };
    }
}
