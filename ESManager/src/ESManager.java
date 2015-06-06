
import fr.cyann.eduspider.manager.Constant;
import fr.cyann.eduspider.manager.Manager;
import fr.cyann.eduspider.mqtt.message.BooleanAttribute;
import fr.cyann.eduspider.mqtt.message.ByteBuffer;
import fr.cyann.eduspider.mqtt.message.CharAttribute;
import fr.cyann.eduspider.mqtt.message.Command;
import fr.cyann.eduspider.mqtt.message.IntegerArrayAttribute;
import fr.cyann.eduspider.mqtt.message.IntegerAttribute;
import fr.cyann.eduspider.mqtt.message.Message;
import fr.cyann.eduspider.mqtt.message.MessageId;
import fr.cyann.eduspider.mqtt.message.StringAttribute;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
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

		String broker = "tcp://localhost:1883";

		MqttClient client = new MqttClient(broker, "spider", new MemoryPersistence());
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		System.out.println("EMITTER Connecting to broker: " + broker);
		client.connect(connOpts);
		System.out.println("EMITTER Connected");

		// create command
		Message command = new Message(255, new Command(Command.Types.MOVE_FRONT));
		command.addArgument(new BooleanAttribute(true));
		command.addArgument(new IntegerAttribute(7));
		command.addArgument(new CharAttribute('a'));
		command.addArgument(new IntegerArrayAttribute(new int[]{
			1, 2, 3, 4
		}));
		command.addArgument(new StringAttribute("Hi mqtt!"));

		ByteBuffer buffer = new ByteBuffer();
		command.generate(buffer);

		client.publish(TOPIC_MAIN, buffer.toArray(), 2, false);
		System.out.println("EMITTER Message sent " + TOPIC_MAIN + ":\n" + buffer.toString() + "\n");

		synchronized (this) {
			this.wait(500);
		}

		System.exit(0);
	}

	public static void main(String[] args) throws MqttException, InterruptedException {
		new ESManager().run();
	}

}
