package model.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.VendedorDao;
import model.enteties.Departamento;
import model.enteties.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection conn;

	/* Quando a classe for instanciada o mesmo recebera uma conexão */
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vendedor findById(Integer id) {

		System.out.println("Id do vendedor: " + id);

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			// Se haver algum resultado no banco de dados o mesmo sera true e executara a
			// pesquisa
			if (rs.next()) {

				/*
				 * Esta sendo instanciado para que o codigo fique mais organizado
				 * instDepartamento
				 */
				Departamento dep = instDepartamento(rs);

				/*
				 * Esta sendo instanciado para que o codigo fique mais organizado instVendedor
				 */
				Vendedor vend = instVendedor(rs, dep);

				return vend;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			/* Fecha as conexoes do st e rs */
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Vendedor instVendedor(ResultSet rs, Departamento dep) throws SQLException {

		Vendedor vend = new Vendedor();
		vend.setId(rs.getInt("DepartmentId"));
		vend.setNome(rs.getString("Name"));
		vend.setEmail(rs.getString("Email"));
		vend.setSalarioBase(rs.getDouble("BaseSalary"));
		vend.setDataNascimento(rs.getDate("BirthDate"));
		vend.setDepartamento(dep);

		return vend;
	}

	private Departamento instDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));

		return dep;
	}

	@Override
	public List<Vendedor> finAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department " + "ON seller.DepartmentId = department.Id ORDER BY Name");

			rs = st.executeQuery();

			List<Vendedor> list = new ArrayList<>();

			/* Sera comparado se esta havendo repetição dos dados pesquisado */
			Map<Integer, Departamento> map = new HashMap<>();

			/* Lista de resultados */
			while (rs.next()) {

				/* Se o departamento n estiver instaciado o dep recebera null */
				Departamento dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					/*
					 * Esta sendo instanciado para que o codigo fique mais organizado
					 * instDepartamento
					 */
					dep = instDepartamento(rs);
					/* Sera salvo o departamento em map para n ser mais null */
					map.put(rs.getInt("DepartmentId"), dep);
				}

				/*
				 * Esta sendo instanciado para que o codigo fique mais organizado instVendedor
				 */
				Vendedor vend = instVendedor(rs, dep);
				list.add(vend);

			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			/* Fecha as conexoes do st e rs */
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	/*
	 * Listar todos os vendedores dos departamento sem repetir o departamento com o
	 * Map<>
	 */
	@Override
	public List<Vendedor> findByIdDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name");

			st.setInt(1, departamento.getId());
			rs = st.executeQuery();

			List<Vendedor> list = new ArrayList<>();

			/* Sera comparado se esta havendo repetição dos dados pesquisado */
			Map<Integer, Departamento> map = new HashMap<>();

			/* Lista de resultados */
			while (rs.next()) {

				/* Se o departamento n estiver instaciado o dep recebera null */
				Departamento dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					/*
					 * Esta sendo instanciado para que o codigo fique mais organizado
					 * instDepartamento
					 */
					dep = instDepartamento(rs);
					/* Sera salvo o departamento em map para n ser mais null */
					map.put(rs.getInt("DepartmentId"), dep);
				}

				/*
				 * Esta sendo instanciado para que o codigo fique mais organizado instVendedor
				 */
				Vendedor vend = instVendedor(rs, dep);
				list.add(vend);

			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			/* Fecha as conexoes do st e rs */
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
