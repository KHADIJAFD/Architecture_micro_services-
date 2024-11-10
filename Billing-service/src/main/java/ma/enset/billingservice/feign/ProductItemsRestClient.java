package ma.enset.billingservice.feign;

import ma.enset.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICES")
public interface ProductItemsRestClient {
    @GetMapping("/products")
    PagedModel<Product> pageProducts();

    @GetMapping(path = "/product/{id}")
    Product getProductById(@PathVariable(name = "id") Long id);
}
