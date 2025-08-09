package partB.productCatalog;


import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<partB.productCatalog.Product, Long> {
}