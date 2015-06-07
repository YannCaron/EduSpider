
import fr.cyann.eduspider.manager.Constant;
import fr.cyann.eduspider.manager.Manager;
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
public class ESManager implements Constant, MqttCallback {

	public void run() throws MqttException, InterruptedException {
		Manager manager = new Manager();
		manager.start();

		String broker = "tcp://localhost:1883";

		MqttClient client = new MqttClient(broker, "spider", new MemoryPersistence());
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		System.out.println("EMITTER Connecting to broker: " + broker);
		client.connect(connOpts);
		System.out.println("EMITTER Connected");
		
		client.subscribe(TOPIC_MAIN);

		// create command
		/*Message command = new Message(255, new Command(Command.Types.MOVE_FRONT));
		command.addAttribute(new BooleanAttribute(true));
		command.addAttribute(new IntegerAttribute(7));
		command.addAttribute(new CharAttribute('a'));
		command.addAttribute(new IntegerArrayAttribute(new int[]{
			1, 2, 3, 4
		}));
		command.addAttribute(new StringAttribute("Hi mqtt!"));*/

		Message message = new Message(new Identication(Identication.Types.REGISTER));
		message.addAttribute(new StringAttribute("AL_%d"));
		
		ByteBuffer buffer = new ByteBuffer();
		message.generate(buffer);

		client.publish(TOPIC_MAIN, buffer.toArray(), 2, false);
		System.out.println("EMITTER Message sent " + TOPIC_MAIN + ":\n" + buffer.toString() + "\n");

		synchronized (this) {
			this.wait(1500);
		}

		System.exit(0);
	}

	public static void main(String[] args) throws MqttException, InterruptedException {
		new ESManager().run();
	}

	@Override
	public void connectionLost(Throwable cause) {
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("MESSAGE RECEIVED");
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
	}

}
