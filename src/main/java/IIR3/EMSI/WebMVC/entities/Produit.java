package IIR3.EMSI.WebMVC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @AllArgsConstructor  @NoArgsConstructor  
@Entity
public class Produit {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ref;
	@Column(length = 25)
	@NotEmpty
	@Size(min=2,max=25)
	private String  design;
	@Min(0)
	private double prix;
	@Min(0)
	private int qte;
	
    
}
