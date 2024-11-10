package AdmProducts.services;

import AdmProducts.domain.Product;

import java.util.List;

public interface IProduct {
    public List<Product> getProduct();
    public void saveProduct(Product product);
    public void updateProduct(Product product);
    public void deleteProduct(Integer id);
    public List<Product> getProductLowStock(Integer stock);
    public List<Product> getProductByNameLike(String nombre);
}
