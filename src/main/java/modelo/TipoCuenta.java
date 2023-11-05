package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tipo_cuenta")
@Data
public class TipoCuenta {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "descripcion")
    private String descripcion;
	@Column(name = "moneda")
    private String moneda;

    @OneToOne(mappedBy = "tipoCuenta")
    private Cuenta cuenta;
}
