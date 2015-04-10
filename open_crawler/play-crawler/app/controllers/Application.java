package controllers;

import java.util.Map;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	if(request().method() == "POST"){
    		Map<String, String[]> urlFormData = request().body().asFormUrlEncoded();
    		String seedUrl = urlFormData.get("seedUrl")[0];
    		
    		System.out.println("get seed url: " +  seedUrl);
    		
    		//判断类型，如果是特定后缀结尾，放到file队列中，否则放到page队列
    	}
    	
        return ok(index.render("Your new application is ready."));
    }

}
