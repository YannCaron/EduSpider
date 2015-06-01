/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.manager;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Manager implements Runnable, MqttCallback, Constant {

	@Override
	public synchronized void run() {
		try {
			MqttClient mqtt = new MqttClient(BROKER, MANAGER_NAME, PERSISTENCE);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);

			System.out.println("MANAGER Connecting to broker: " + BROKER);
			mqtt.setCallback(this);
			mqtt.connect(connOpts);
			System.out.println("MANAGER Connected");

			mqtt.subscribe(TOPIC_MAIN, QOS_ONCE);

			while (true) {
				this.wait(500);
			}

		} catch (MqttException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InterruptedException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("MANAGER Connection lost!");
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("MANAGER Message received " + topic + " " + message.toString());
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("MANAGER Delivery complete " + token.toString());
	}

	public static void main(String[] args) {

		Manager manager = new Manager();
		Thread mThread = new Thread(manager);
		mThread.start();

	}

}
