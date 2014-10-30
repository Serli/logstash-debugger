package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import views.html.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application extends Controller {

    //Normalizer.Form<LogstashConfiguration> configForm = Normalizer.Form.form(LogstashConfiguration.class);

    public static Result index() {
        return ok(main.render());
    }

    public static Result submit() throws FileNotFoundException {
        RequestBody body = request().body();
        MultipartFormData mfd = body.asMultipartFormData();
        Map<java.lang.String,java.lang.String[]> input = mfd.asFormUrlEncoded();
        String output = "";
        output=input.get("input")[0]+" "+input.get("config")[0];

        return ok(output);
    }
}
