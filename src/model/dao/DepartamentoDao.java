package model.dao;

import java.util.List;

import model.enteties.Departamento;

public interface DepartamentoDao {

	void insert(Departamento obj);

	void update(Departamento obj);

	void deletById(Integer id);

	Departamento findById(Integer id);

	List<Departamento> finAll();
}
