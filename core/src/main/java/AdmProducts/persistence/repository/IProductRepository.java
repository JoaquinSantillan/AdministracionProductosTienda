package AdmProducts.persistence.repository;

import AdmProducts.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Integer>{

        List<ProductEntity> findByStockLessThan(Integer stock);
        List<ProductEntity> findByNombreContaining(String nombre);
}
