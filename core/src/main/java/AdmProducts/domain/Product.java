package AdmProducts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private Integer id;
    private String nombre;
    private String codigo;
    private String categoria;
    private int cantidad;
    private String proveedor;
    private String ubicacion;
    private int stockMinimo;
}
