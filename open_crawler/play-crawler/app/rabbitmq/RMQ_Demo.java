package rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RMQ_Demo {
	static final String EXCHANGE_NAME = "open_crawler";

	public static void main(String[] args) throws IOException{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		
		channel.basicPublish(EXCHANGE_NAME, "", arg2, arg3, arg4);
	}
}
