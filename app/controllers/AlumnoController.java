package controllers;

import helpers.ControllerHelper;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.Alumno;
import models.Profesor;
import play.data.Form;
import play.*;
import play.mvc.*;
import play.libs.Json;

public class AlumnoController extends Controller 
{
	public Result crearAlumno() 
    {
		Form<Alumno> form = Form.form(Alumno.class).bindFromRequest();

		if (form.hasErrors())
		{
			return badRequest(ControllerHelper.errorJson(2, "invalid_alumno", form.errorsAsJson()));
		}

		Alumno alumno = form.get();
		
		alumno.save();
		
		return created();
    }
	

	public Result getAlumno(String id)
	{	
		Result res;
		
		Alumno alumno = Alumno.find.byId(id);

		if (alumno == null)
		{
			res = notFound();
		}
		else
		{
			if(ControllerHelper.acceptsJson(request()))
			{
				res = ok(Json.toJson(alumno));
			}
			else if(ControllerHelper.acceptsXml(request()))
			{
				res = ok(views.xml.alumno.render(alumno));
			}
			else
			{
				res = badRequest(ControllerHelper.errorJson(1, "unsupported_format", null));
			}
		}
		
		return res;
	}
	
	public Result listarAlumnos()
	{		
		Result res;
		
		List<Alumno> alumnos = Alumno.findAll();

		if(ControllerHelper.acceptsJson(request()))
			{
				res = ok(Json.toJson(alumnos));
			}
			else if(ControllerHelper.acceptsXml(request()))
			{
				res = ok(views.xml.alumnos.render(alumnos));
			}
			else
			{
				res = badRequest(ControllerHelper.errorJson(1, "unsupported_format", null));
			}

		return res;
	}
	

	public Result actualizarAlumno(String id)
	{	
		Alumno alumno = Alumno.find.byId(id);
		
		if (alumno == null) 
		{
			return notFound();
		}
		
		Form<Alumno> form = Form.form(Alumno.class).bindFromRequest();

		if (form.hasErrors()) 
		{
			return badRequest(ControllerHelper.errorJson(1, "invalid_alumno", form.errorsAsJson()));
		}

		Result res;

		if (alumno.changeData(form.get())) 
		{
			alumno.save();
			res = ok();
		}
		else 
		{
			res = status(NOT_MODIFIED);
		}
		
		return res;
	}
	

	public Result eliminarAlumno(String id)
	{
		Alumno alumno = Alumno.find.byId(id);
		if (alumno == null) 
		{
			return notFound();
		}

		alumno.delete();

		return ok();		
	}
}
