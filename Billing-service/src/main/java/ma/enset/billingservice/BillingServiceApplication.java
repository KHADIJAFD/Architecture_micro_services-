package ma.enset.billingservice;

import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.feign.CustomerRestClient;
import ma.enset.billingservice.feign.ProductItemsRestClient;
import ma.enset.billingservice.models.Customer;
import ma.enset.billingservice.models.Product;
import ma.enset.billingservice.repositories.BillRepository;
import ma.enset.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRestClient customerRestClient,
							ProductItemsRestClient productItemsRestClient,
							BillRepository billRepository,
							ProductItemRepository productItemRepository){
		return args -> {
			Customer customer=customerRestClient.getCustomerById(1L);
			Bill bill=billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
			PagedModel<Product> productItemPagedModel=productItemsRestClient.pageProducts();
			productItemPagedModel.forEach(product -> {
				ProductItem productItem=new ProductItem();
				productItem.setPrice(product.getPrice());
				productItem.setQuantity(1+new Random().nextInt(100));
				productItem.setBill(bill);
				productItem.setId(product.getId());
				productItemRepository.save(productItem);

			});
		};

	}
}
