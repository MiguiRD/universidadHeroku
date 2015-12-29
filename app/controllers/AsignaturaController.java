package controllers;

import helpers.ControllerHelper;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.Alumno;
import models.Profesor;
import models.Asignatura;
import play.data.Form;
import play.cache.Cache;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class AsignaturaController extends Controller {

	public Result crearProfesor() 
	{
		Form<Profesor> form = Form.form(Profesor.class).bindFromRequest();

		if (form.hasErrors())
		{
			return badRequest(ControllerHelper.errorJson(2, "invalid_profesor", form.errorsAsJson()));
		}

		Profesor profesor = form.get();
		
		profesor.save();
		
		return created();

	}

	public Result asignarAsignaturaAProfesor(String idAs, String idP) 
	{
		Asignatura asignatura = Asignatura.find.byId(idAs);

		if (asignatura == null)
		{
			return ok("Asignatura no existente");
		}
		else
		{
			Profesor profesor = Profesor.find.byId(idP);

			if (profesor == null)
			{
				return ok("Profesor no existente");
			}
			else
			{
				asignatura.setProfesor(profesor);
				
				profesor.save();
				
				return ok("Asignatura asignada correctamente");
			}
		}
	}

	public Result crearAsignatura() 
	{
		Form<Asignatura> form = Form.form(Asignatura.class).bindFromRequest();

		if (form.hasErrors())
		{
			return badRequest(ControllerHelper.errorJson(2, "invalid_asignatura", form.errorsAsJson()));
		}

		Asignatura asignatura = form.get();
		
		asignatura.save();
		
		return created();
	}

	public Result getAsignatura(String id) 
	{

		Result res;
		
		Asignatura asignatura = Asignatura.find.byId(id);

		if (asignatura == null)
		{
			res = notFound();
		}
		else
		{
			if(ControllerHelper.acceptsJson(request()))
			{
				res = ok(Json.toJson(asignatura));
			}
			else if(ControllerHelper.acceptsXml(request()))
			{
				res = ok(views.xml.asignatura.render(asignatura));
			}
			else
			{
				res = badRequest(ControllerHelper.errorJson(1, "unsupported_format", null));
			}
		}
		
		return res;

	}

	public Result eliminarAsignatura(String id) 
	{
		Asignatura asignatura = Asignatura.find.byId(id);
		
		if (asignatura == null) 
		{
			return notFound();
		}

		asignatura.delete();

		return ok();	
	}

	public Result actualizarAsignatura(String id) 
	{
		Asignatura asignatura = Asignatura.find.byId(id);
		
		if (asignatura == null) 
		{
			return notFound();
		}
		
		Form<Asignatura> form = Form.form(Asignatura.class).bindFromRequest();

		if (form.hasErrors()) 
		{
			return badRequest(ControllerHelper.errorJson(1, "invalid_asignatura", form.errorsAsJson()));
		}

		Result res;

		if (asignatura.changeData(form.get())) 
		{
			asignatura.save();
			res = ok();
		}
		else 
		{
			res = status(NOT_MODIFIED);
		}
		
		return res;
	}

	public Result listarAsignaturas() {

		Result res;

		List<Asignatura> asignaturas = Asignatura.findAll();

		if (ControllerHelper.acceptsJson(request()))
		{
			res = ok(Json.toJson(asignaturas));
		} 
		else if (ControllerHelper.acceptsXml(request())) 
		{
			res = ok(views.xml.asignaturas.render(asignaturas));
		} 
		else 
		{
			res = badRequest(ControllerHelper.errorJson(1,"unsupported_format", null));
		}

		return res;
	}


	public Result asignarAlumnoAAsignatura(String idAl, String idAs) 
	{
		Alumno alumno = Alumno.find.byId(idAl);

		if (alumno == null)
		{
			return ok("Alumno no existente");
		}
		else
		{
			Asignatura asignatura = Asignatura.find.byId(idAs);

			if (asignatura == null)
			{
				return ok("Asignatura no existente");
			}
			else
			{
				List<Alumno> alumnos = asignatura.getAlumnos();

				alumnos.add(alumno);

				asignatura.setAlumnos(alumnos);

				asignatura.save();
				
				return ok("Asignatura asignada correctamente");
			}
		}
	}
}
