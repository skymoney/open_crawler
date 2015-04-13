package rabbitmq;

import java.io.Serializable;

/**
 * 封装消息
 * */
public class UrlPage implements Serializable{
	private String urlName;
	private String url;
	private String type;

	public UrlPage(String urlName, String url){
		this.urlName = urlName;
		this.url = url;
	}
	
	public UrlPage(){}
	
	public String getUrlName() {
		return urlName;
	}
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
}
