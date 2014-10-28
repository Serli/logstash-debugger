package controllers;

import model.forms.LogstashConfiguration;
import play.*;
import play.mvc.*;

import views.html.*;

import java.text.Normalizer;
import java.util.Map;

public class Application extends Controller {

    Form<LogstashConfiguration> configForm = Normalizer.Form.form(LogstashConfiguration.class);

    public static Result index() {
        return ok(index.render("Mostach"));
    }

    public static Result submit(){
        Map<String,Object> anyData = new HashMap();
        anyData.put("input", "bob@gmail.com");
        anyData.put("configFile", "secret");
    }
}
