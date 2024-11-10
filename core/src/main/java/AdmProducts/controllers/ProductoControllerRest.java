package AdmProducts.controllers;

import AdmProducts.domain.Product;
import AdmProducts.persistence.entity.ProductEntity;
import AdmProducts.services.ProductService;
import jakarta.servlet.Servlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductoControllerRest {

    @Autowired
    private ProductService productosServices;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Controller is working");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts()
    {
        List<Product> productos = productosServices.getProduct();

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<?> getProductLowStock(@RequestParam Integer stock)
    {
        List<Product> products = productosServices.getProductLowStock(stock);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProductByName(@RequestParam String nombre)
    {
        List<Product> products = productosServices.getProductByNameLike(nombre);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<?> postProducts(@RequestBody Product product)
    {
        productosServices.saveProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).body(product);
    }

    @PutMapping
    public ResponseEntity<?> putProducts(@RequestBody Product product)
    {
        productosServices.updateProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id)
    {
        productosServices.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }


}
