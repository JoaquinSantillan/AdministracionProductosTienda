package AdmProducts.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="products")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String codigo;
    private String categoria;
    private int cantidad;
    private String proveedor;
    private String ubicacion;
    private int stockMinimo;

}
