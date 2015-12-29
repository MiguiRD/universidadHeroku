package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Find;

@Entity
public class Asignatura extends Model
{
	@Id
	public String id;
	
	@Required
	public String nombre;
	public Integer curso;

	@ManyToMany(mappedBy="asignaturas", cascade = CascadeType.ALL)
	private List<Alumno> alumnos;
	
	@ManyToOne
	private Profesor profesor;
	
	public static final Find<String, Asignatura> find = new Find <String, Asignatura>(){};
	
	public boolean changeData(Asignatura newData) 
	{
		boolean changed = false;
		
		if (newData.nombre != null) {
			this.nombre = newData.nombre;
			changed = true;
		}
		
		if (newData.curso != null) 
		{
			this.curso = newData.curso;
			changed = true;
		}
		
		
		return changed;
	}
	
	public static List<Asignatura> findAll()
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

	public Integer getCurso() 
	{
		return curso;
	}

	public void setCurso(Integer curso) 
	{
		this.curso = curso;
	}

	public List<Alumno> getAlumnos() 
	{
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) 
	{
		this.alumnos = alumnos;
	}

	public Profesor getProfesor() 
	{
		return profesor;
	}

	public void setProfesor(Profesor profesor) 
	{
		this.profesor = profesor;
	}

	public static Find<String, Asignatura> getFind() {
		return find;
	}

}
