package models;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import play.data.Form;
import play.libs.Json;

public class ProfesorTest 
{
	@Test
    public void nombreEsRequerido()
    {
		running(fakeApplication(inMemoryDatabase()), () ->
		{
			Profesor profesor = new Profesor();
			profesor.setApellido1("apellido1");
			profesor.setApellido2("apellido2");
			
    		Form<Profesor> form = Form.form(Profesor.class).bind(Json.toJson(profesor));
    		
    		assertTrue(form.hasErrors());	
			assertEquals(1, form.field("nombre").errors().size());
			assertTrue(form.field("apellido1").errors().isEmpty());
			assertTrue(form.field("apellido2").errors().isEmpty());
		});
    }
	
	@Test
    public void apellido1EsRequerido()
    {
		running(fakeApplication(inMemoryDatabase()), () ->
		{
			Profesor profesor = new Profesor();
			profesor.setNombre("nombre");
			profesor.setApellido2("apellido2");
			
    		Form<Profesor> form = Form.form(Profesor.class).bind(Json.toJson(profesor));
    		
    		assertTrue(form.hasErrors());	
			assertEquals(2, form.field("apellido1").errors().size());
			assertTrue(form.field("nombre").errors().isEmpty());
			assertTrue(form.field("apellido2").errors().isEmpty());
		});
    }
	
	@Test
    public void apellido2EsRequerido()
    {
		running(fakeApplication(inMemoryDatabase()), () ->
		{
			Profesor profesor = new Profesor();
			profesor.setApellido1("nombre");
			profesor.setApellido2("apellido1");
			
    		Form<Profesor> form = Form.form(Profesor.class).bind(Json.toJson(profesor));
    		
    		assertTrue(form.hasErrors());	
			assertEquals(3, form.field("apellido2").errors().size());
			assertTrue(form.field("nombre").errors().isEmpty());
			assertTrue(form.field("apellido1").errors().isEmpty());
		});
    }
	    
    @Test
    public void guardarProfesor()
    {
		running(fakeApplication(inMemoryDatabase()), () ->
		{
			Profesor profesor = insertarProfesor("P001", "nombre", "apellido1", "apellido2");
			
			Profesor profesorGuardado = Profesor.find.byId(profesor.getId());
			
			assertEquals(profesor.getNombre(), profesorGuardado.getNombre());
		});
    }
    
    @Test
    public void borrarProfesor()
    {
		running(fakeApplication(inMemoryDatabase()), () ->
		{
			Profesor profesor = insertarProfesor("P001", "nombre", "apellido1", "apellido2");
			
			Profesor savedPlace = Profesor.find.byId(profesor.getId());
			savedPlace.delete();
			
			assertNull(Profesor.find.byId(profesor.getId()));
		});
    }
    
    private Profesor insertarProfesor(String id, String nombre, String apellido1, String apellido2)
    {
		Profesor profesor = new Profesor();
		profesor.setId(id);
		profesor.setNombre(nombre);
		profesor.setApellido1(apellido1);
		profesor.setApellido2(apellido2);
		profesor.save();
		
		return profesor;
    }

}
