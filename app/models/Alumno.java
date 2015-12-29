package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints.Required;
import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Find;


@Entity
public class Alumno extends Model
{
	@Id
	public String id;
	
	@Required
	public String nombre;
	public String apellido1;
	public String apellido2;
	
	@ManyToMany(cascade = CascadeType.ALL)
	public  List<Asignatura> asignaturas;
	
	
	public static final Find<String, Alumno> find = new Find <String, Alumno>(){};
	
	public boolean changeData(Alumno newData) 
	{
		boolean changed = false;
		
		if (newData.nombre != null) {
			this.nombre = newData.nombre;
			changed = true;
		}
		
		if (newData.apellido1 != null) 
		{
			this.apellido1 = newData.apellido1;
			changed = true;
		}
		
		if (newData.apellido2 != null) 
		{
			this.apellido2 = newData.apellido2;
			changed = true;
		}
		
		return changed;
	}
	
	public static List<Alumno> findAll()
	{
		return find.findList();
	}

	
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

	public static Find<String, Alumno> getFind() {
		return find;
	}

}
