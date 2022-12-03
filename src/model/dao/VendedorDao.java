package model.dao;

import java.util.List;

import model.enteties.Vendedor;

public interface VendedorDao {

	void insert(Vendedor obj);

	void update(Vendedor obj);

	void deletById(Integer id);

	Vendedor findById(Integer id);

	List<Vendedor> finAll();

}
