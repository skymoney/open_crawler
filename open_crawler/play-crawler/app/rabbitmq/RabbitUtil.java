package rabbitmq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RabbitUtil {
	/**
	 * parse obj to byte[]
	 * */
	public static byte[] toBytes(Object urlPage){
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(urlPage);
			
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (IOException e) {
		}
		return null;		
	}
	
	/**
	 * from byte[] to obejct
	 * */
	public static Object fromBytes(byte[] bytes){
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			
			return ois.readObject();
		} catch (Exception e) {}
		
		return null;
	}
}
