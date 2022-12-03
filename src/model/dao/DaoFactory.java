package model.dao;

import model.dao.imp.VendedorDaoJDBC;

public class DaoFactory {

	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoJDBC();
	}
}
