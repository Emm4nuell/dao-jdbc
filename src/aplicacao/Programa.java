package aplicacao;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.enteties.Departamento;
import model.enteties.Vendedor;

public class Programa {

	public static void main(String[] args) {

		/*
		 * Departamento dp = new Departamento(1, "Livros");
		 * 
		 * Vendedor vendedor = new Vendedor(21, "Eduardo", "eduardo.eesf@gmail.com", new
		 * Date(), 4700.0, dp);
		 * 
		 * System.out.println(dp); System.out.println(vendedor);
		 */
		
		VendedorDao vdao = DaoFactory.criarVendedorDao();
		
		System.out.println("=== Teste 1 : finById ===");
		Vendedor vendedor = vdao.findById(3);
		System.out.println(vendedor);
	}

}
