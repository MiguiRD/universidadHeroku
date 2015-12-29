package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints.Required;

import com.avaje.ebean.Model;

@Entity
public class Profesor extends Model
{
	@Id
	public String id;
	
	@Required
	public String nombre;
	public String apellido1;
	public String apellido2;
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="profesor")
	@JsonIgnore
	private List<Asignatura> asignaturas;

	public static final Find<String, Profesor> find = new Find <String, Profesor>(){};
	

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getApellido1() 
	{
		return apellido1;
	}

	public void setApellido1(String apellido1) 
	{
		this.apellido1 = apellido1;
	}

	public String getApellido2() 
	{
		return apellido2;
	}

	public void setApellido2(String apellido2) 
	{
		this.apellido2 = apellido2;
	}

	public List<Asignatura> getAsignaturas() 
	{
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) 
	{
		this.asignaturas = asignaturas;
	}

	public static Find<String, Profesor> getFind() {
		return find;
	}

}
