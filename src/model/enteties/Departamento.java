package model.enteties;

import java.io.Serializable;
import java.util.Objects;

public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int Id;
	private String name;

	public Departamento() {
	}

	public Departamento(int id, String name) {
		super();
		Id = id;
		this.name = name;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* Sera necessário verificar qual finalidade do hashCode e equals */
	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Id == other.Id;
	}

	@Override
	public String toString() {
		return "Departament [Id=" + Id + ", name=" + name + "]";
	}

}
