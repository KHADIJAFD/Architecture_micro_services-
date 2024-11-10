package ma.enset.billingservice.web;

import lombok.AllArgsConstructor;
import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.feign.CustomerRestClient;
import ma.enset.billingservice.feign.ProductItemsRestClient;
import ma.enset.billingservice.models.Customer;
import ma.enset.billingservice.models.Product;
import ma.enset.billingservice.repositories.BillRepository;
import ma.enset.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BillingRestController {
    BillRepository billRepository;
    ProductItemRepository productItemRepository;
    CustomerRestClient customerRestClient;
    ProductItemsRestClient productItemsRestClient;

    @GetMapping("/bill/{id}")
    Bill getBillById(@PathVariable(name = "id") Long id){
     Bill bill=billRepository.findById(id).get();
     Customer customer=customerRestClient.getCustomerById(bill.getCustomerId());
     bill.setCustomer(customer);
    bill.getProductItems().forEach(productItem -> {
        Product product=productItemsRestClient.getProductById(productItem.getProductId());
        //productItem.setProduct(product);
        productItem.setProductName(product.getName());
    });
     return  bill;

    }


}
