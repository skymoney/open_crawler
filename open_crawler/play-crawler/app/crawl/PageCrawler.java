package crawl;

public class PageCrawler extends Thread{
	private String url;
	public PageCrawler(String url){
		this.url = url;
	}
	
	public void start(){
		System.out.println("========start to crawl page" + url);
	}
}
