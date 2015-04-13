package rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class RabbitClient {
	private static ConnectionFactory factory;
	private static Channel channel = null;
	private static Connection connection;
	private static QueueingConsumer pageConsumer;
	private static final String EXCHANGE_NAME = "play_crawler";
	
	private static final String PAGE_QUEUE = "page";
	private static final String FILE_QUEUE = "file";
	
	private RabbitClient(){}
	
	public static Channel getInstance(){
		if(factory==null)
			factory = new ConnectionFactory();
		factory.setHost("localhost");
		//factory.setPort(6572);
		try {
			if(connection == null)
				connection = factory.newConnection();
			//if(connection.isOpen())
			channel = connection.createChannel();
			
			channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);
			
			channel.queueDeclare(PAGE_QUEUE, false, false, false, null);
			channel.queueBind(PAGE_QUEUE, EXCHANGE_NAME, "page");
			
			channel.queueDeclare(FILE_QUEUE, false, false, false, null);
			channel.queueBind(FILE_QUEUE, EXCHANGE_NAME, "file");
			
			return channel;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String consumePage(){
		channel = getInstance();
		pageConsumer = new QueueingConsumer(channel);
		try {
			channel.basicConsume(PAGE_QUEUE, true, pageConsumer);
			
			QueueingConsumer.Delivery deliver = pageConsumer.nextDelivery();
			UrlPage urlPage = (UrlPage)RabbitUtil.fromBytes(deliver.getBody());
			
			return urlPage.getType() + " : " + urlPage.getUrl();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ShutdownSignalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConsumerCancelledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
