
import fr.cyann.eduspider.manager.Constant;
import fr.cyann.eduspider.manager.Manager;
import fr.cyann.eduspider.mqtt.MessageManager;
import fr.cyann.eduspider.mqtt.message.ByteBuffer;
import fr.cyann.eduspider.mqtt.message.Identication;
import fr.cyann.eduspider.mqtt.message.Message;
import fr.cyann.eduspider.mqtt.message.StringAttribute;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 <p>
 @author cyann
 */
public class ESManager implements Constant {

	public void run() throws MqttException, InterruptedException {
		Manager manager = new Manager();
		manager.start();

		MessageManager spider = new MessageManager(BROKER, "spider2");
		spider.connect();
		spider.subscribe(TOPIC_MAIN);

		String topic = "myTopic";

		Message message = new Message(new Identication(Identication.Types.REGISTER));
		message.addAttribute(new StringAttribute("AL_%d"));
		message.addAttribute(new StringAttribute(topic));

		spider.publish(TOPIC_MAIN, message);

		//while (true) {
			synchronized (this) {
				this.wait(250);
			}
		//}

		System.exit(0);
	}

	public static void main(String[] args) throws MqttException, InterruptedException {
		new ESManager().run();
	}

}
