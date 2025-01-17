package ma.enset.billingservice.repositories;

import ma.enset.billingservice.entities.ProductItem;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
    Collection<ProductItem> findByBillId(Long id);
}
