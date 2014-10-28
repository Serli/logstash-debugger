package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import views.html.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Scanner;

public class Application extends Controller {

    //Normalizer.Form<LogstashConfiguration> configForm = Normalizer.Form.form(LogstashConfiguration.class);

    public static Result index(String title) {
        return ok(index.render(title));
    }

    public static Result submit() throws FileNotFoundException {
        RequestBody body = request().body();
        MultipartFormData mfd = body.asMultipartFormData();
        List<MultipartFormData.FilePart> l = mfd.getFiles();
        File f = l.get(0).getFile();
        Scanner scan = new Scanner(f);
        String output = "";
        while(scan.hasNext()){
            output+=scan.next();
        }

        return ok(output);
    }
}
