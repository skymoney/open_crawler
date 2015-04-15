package controllers;

import java.util.Map;

import crawl.PageCrawler;
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
    		
    		//RabbitServer.publish("play_crawler", urlPage);
    		
    		return ok("seed url received");
    	}
    	
        return ok(index.render("Open Crawler"));
    }

    public static Result monitor(){
    	return ok(monitor.render("Monitor Task"));
    }
    
    public static Result submit(){
    	Map<String, String[]> formData = request().body().asFormUrlEncoded();
    	String seedUrl = formData.get("seedUrl")[0];
    	
    	//提交任务，并启动
    	UrlPage urlPage = new UrlPage("", seedUrl);
    	urlPage.setType("page");
    	
    	RabbitServer.publish("play_crawler", urlPage);
    	int taskCount = 0;
    	taskCount += 1;
    	
    	while(taskCount != 0){
    		
    		String url = RabbitClient.consumePage();
    		System.out.println("Get page url " + url);
    		PageCrawler pc = new PageCrawler(url);
    		pc.start();
    		
    		taskCount --;
    	}
    	
    	return ok(seedUrl);
    }
    
    public static Result statPage(){
    	String pageInfo = RabbitClient.consumePage();
    	return ok("stat page here \n " + pageInfo);
    }
    
    public static Result statFile(){
    	return ok("stat file here");
    }
}
