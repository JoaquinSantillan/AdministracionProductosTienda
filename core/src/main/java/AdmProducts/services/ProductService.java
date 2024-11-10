package AdmProducts.services;

import AdmProducts.domain.Product;
import AdmProducts.persistence.entity.ProductEntity;
import AdmProducts.persistence.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("BD")
//@ConditionalOnProperty(value = "productos.estrategia",havingValue = "EN_BD")
public class ProductService implements IProduct{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getProduct() {
        List<Product> productos = productRepository.findAll().
                                        stream().map(productosEntity->
                                        {
                                            Product producto = new Product();
                                            producto.setId(productosEntity.getId());
                                            producto.setNombre(productosEntity.getNombre());
                                            producto.setCodigo(productosEntity.getCodigo());
                                            producto.setStockMinimo(productosEntity.getStockMinimo());
                                            producto.setCantidad(productosEntity.getCantidad());
                                            producto.setCategoria(productosEntity.getCategoria());
                                            producto.setProveedor(productosEntity.getProveedor());
                                            return producto;
                                        }).collect(Collectors.toList());

        return productos;
    }

    @Override
    public List<Product> getProductLowStock(Integer stock) {
        List<Product> productos = productRepository.findByStockLessThan(stock).
                stream().map(productosEntity->
                {
                    Product producto = new Product();
                    producto.setId(productosEntity.getId());
                    producto.setNombre(productosEntity.getNombre());
                    producto.setCodigo(productosEntity.getCodigo());
                    producto.setStockMinimo(productosEntity.getStockMinimo());
                    producto.setCantidad(productosEntity.getCantidad());
                    producto.setCategoria(productosEntity.getCategoria());
                    producto.setProveedor(productosEntity.getProveedor());
                    return producto;
                }).collect(Collectors.toList());

        return productos;
    }

    @Override
    public List<Product> getProductByNameLike(String nombre) {
        List<Product> productos = productRepository.findByNombreContaining(nombre).
                stream().map(productosEntity->
                {
                    Product producto = new Product();
                    producto.setId(productosEntity.getId());
                    producto.setNombre(productosEntity.getNombre());
                    producto.setCodigo(productosEntity.getCodigo());
                    producto.setStockMinimo(productosEntity.getStockMinimo());
                    producto.setCantidad(productosEntity.getCantidad());
                    producto.setCategoria(productosEntity.getCategoria());
                    producto.setProveedor(productosEntity.getProveedor());
                    return producto;
                }).collect(Collectors.toList());

        return productos;
    }

    @Override
    public void saveProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setNombre(product.getNombre());
        productEntity.setCodigo(product.getCodigo());
        productEntity.setStockMinimo(product.getStockMinimo());
        productEntity.setCantidad(product.getCantidad());
        productEntity.setCategoria(product.getCategoria());
        productEntity.setProveedor(product.getProveedor());

        productRepository.save(productEntity);
    }

    @Override
    public void updateProduct(Product product) {
        ProductEntity findProduct = productRepository.findById(product.getId()).orElseThrow(()-> new RuntimeException("error producto no encontrado"));

        findProduct.setNombre(product.getNombre());
        findProduct.setCodigo(product.getCodigo());
        findProduct.setStockMinimo(product.getStockMinimo());
        findProduct.setCantidad(product.getCantidad());
        findProduct.setCategoria(product.getCategoria());
        findProduct.setProveedor(product.getProveedor());

        productRepository.save(findProduct);
    }

    @Override
    public void deleteProduct(Integer id) {
        if(!productRepository.existsById(id))
        {
            throw new RuntimeException("producto no encontrado");
        }
        productRepository.deleteById(id);
    }

}
