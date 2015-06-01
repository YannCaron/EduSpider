/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poc_mqtt_java;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 <p>
 @author cyann
 */
public class Client1 implements Runnable, Constant {

	@Override
	public synchronized void run() {
		try {
			MqttClient sampleClient = new MqttClient(broker, "spider", persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("EMITTER Connecting to broker: " + broker);
			sampleClient.connect(connOpts);
			System.out.println("EMITTER Connected");

			while (true) {
				String content = "Message from spider";
				System.out.println("EMITTER Publishing message: " + content);
				MqttMessage message = new MqttMessage(content.getBytes());
				message.setQos(qos);
				sampleClient.publish(topic, message);
				System.out.println("EMITTER Message published");

				this.wait(500);
			}

		} catch (MqttException me) {
			System.out.println("EMITTER reason " + me.getReasonCode());
			System.out.println("EMITTER msg " + me.getMessage());
			System.out.println("EMITTER loc " + me.getLocalizedMessage());
			System.out.println("EMITTER cause " + me.getCause());
			System.out.println("EMITTER excep " + me);
			me.printStackTrace();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

}
