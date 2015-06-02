
import fr.cyann.eduspider.manager.Constant;
import fr.cyann.eduspider.manager.Manager;
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
 * <p>
 * @author cyann
 */
public class ESManager implements Constant {

	public void run() throws MqttException, InterruptedException {
		Manager manager = new Manager();
		manager.start();

		String broker = "tcp://localhost:1883";

		MqttClient sampleClient = new MqttClient(broker, "spider", new MemoryPersistence());
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		System.out.println("EMITTER Connecting to broker: " + broker);
		sampleClient.connect(connOpts);
		System.out.println("EMITTER Connected");

		byte[] content = new byte[] {
			(byte)0x01, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff,
			(byte)0x02, (byte)0x00, (byte)0x01, (byte)0x03,
			(byte)0xa2, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x32
		};

		/*int[] content = new int[] {
		 0x01, 0x04, 0x00, 0x00, 0x05, 0xff
		 };*/
		sampleClient.publish(TOPIC_MAIN, content, 0, false);

		synchronized (this) {
			this.wait(500);
		}

		System.exit(0);
	}

	public static void main(String[] args) throws MqttException, InterruptedException {
		new ESManager().run();
	}

}
