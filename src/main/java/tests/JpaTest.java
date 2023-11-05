package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import modelo.Cliente;
import modelo.Cuenta;
import modelo.Movimiento;
import modelo.Rol;
import modelo.TipoCuenta;

public class JpaTest {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();
		Rol rolTitular = new Rol();
        rolTitular.setDescripcion("Titular");
        rolTitular.setActivo(true);

       

        manager.persist(rolTitular);
       

        
        Cliente cli = new Cliente();
        cli.setApellidoMaterno("Rios");
        cli.setApellidoPaterno("Ruiz");
        cli.setNombres("Jerson");
        cli.setCuentas(new ArrayList());
        cli.setRoles(new ArrayList());

        
        cli.getRoles().add(rolTitular);
        

        manager.persist(cli);

        
        Cuenta cuent = new Cuenta();
        cuent.setNumeroCuenta("564853123");
        cuent.setSaldo(10000d);
        cuent.setActiva(true);

        TipoCuenta tipoCuent = new TipoCuenta();
        tipoCuent.setDescripcion("Cuenta Ahorros");
        tipoCuent.setMoneda("Soles");

        Movimiento movimiento = new Movimiento();
        movimiento.setDescripcion("Deposito Recurrente");
        movimiento.setFecha(LocalDate.now());
        movimiento.setMonto(500d);

        
        cuent.setTipoCuenta(tipoCuent);
        cuent.setCliente(cli);
        movimiento.setCuenta(cuent);

        
        manager.persist(cuent);
        manager.persist(tipoCuent);
        manager.persist(movimiento);

        tx.commit();

        List<Cliente> lista = manager.createQuery("from Cliente", Cliente.class).getResultList();
        for (Cliente c : lista) {
            System.out.println(c);
        }

	}

}
