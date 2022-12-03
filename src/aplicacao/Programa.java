package aplicacao;

import java.util.Date;

import model.enteties.Departamento;
import model.enteties.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		Departamento dp = new Departamento(1, "Livros");
		
		Vendedor vendedor = new Vendedor(21, "Eduardo", "eduardo.eesf@gmail.com", new Date(), 4700.0, dp);
		
		System.out.println(dp);
		System.out.println(vendedor);

	}

}
