package rabbitmq;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitServer {	
	private static Channel channel = null;
	static final String EXCHANGE_NAME = "play_crawler";
	public RabbitServer(){
		ConnectionFactory factory = new ConnectionFactory();
		
	}
	
	public static Channel getInstance(){
		if(channel != null){
			return channel;
		}
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		//factory.setPort(6572);
		
		Connection connection;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			
			channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);
		} catch (IOException e) {
			return null;
		}
		
		return channel;
	}
	
	public static void publish(String exchangeName, Object message){
		channel = RabbitServer.getInstance();
		
		try {
			channel.basicPublish(exchangeName, ((UrlPage)message).getType(), null,RabbitUtil.toBytes(message));
			//System.out.println("Sent to " + exchangeName + " : " + ((UrlPage)message).getUrl());
		} catch (IOException e) {
			
		}
	}
}
