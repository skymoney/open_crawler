package controllers;

import java.util.Map;

import play.*;
import play.mvc.*;
import rabbitmq.RabbitClient;
import rabbitmq.RabbitServer;
import rabbitmq.UrlPage;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	if(request().method() == "POST"){
    		Map<String, String[]> urlFormData = request().body().asFormUrlEncoded();
    		
    		String seedUrl = urlFormData.get("seedUrl")[0];    		
    		//判断类型，如果是特定后缀结尾，放到file队列中，否则放到page队列
    		
    		UrlPage urlPage = new UrlPage("", seedUrl);
    		urlPage.setType("page");
    		
    		RabbitServer.publish("play_crawler", urlPage);
    		
    		return ok("seed url received");
    	}
    	
        return ok(index.render("Open Crawler"));
    }

    public static Result statPage(){
    	String pageInfo = RabbitClient.consumePage();
    	return ok("stat page here \n " + pageInfo);
    }
    
    public static Result statFile(){
    	return ok("stat file here");
    }
}
