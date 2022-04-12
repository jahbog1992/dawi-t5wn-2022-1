package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 
import lombok.Data; 

@Entity
@Table(name = "tb_productos")
@Data
public class Producto {

	@Id
	private String id_prod;  
	private String des_prod; 
	private int stk_prod; 
	private double pre_prod;  
    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")
    private Categoria categoria; 
	private int est_prod;  
    @ManyToOne
    @JoinColumn(name = "idprovedor", referencedColumnName = "idprovedor")
    private Proveedor proveedor;  
}
