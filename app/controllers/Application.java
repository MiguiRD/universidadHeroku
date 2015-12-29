package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok("Bienvenido a la universidad Miguel Regalado - Carlos Ramos");
    }

}
