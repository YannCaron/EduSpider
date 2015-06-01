/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poc_mqtt_java;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import static poc_mqtt_java.Constant.broker;
import static poc_mqtt_java.Constant.persistence;
import static poc_mqtt_java.Constant.qos;
import static poc_mqtt_java.Constant.topic;

/**
 <p>
 @author cyann
 */
public class Client2 implements Runnable, Constant, MqttCallback {

	@Override
	public synchronized void run() {
		try {
			MqttClient mqtt = new MqttClient(broker, "algoidLanguage", persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("LISTENER Connecting to broker: " + broker);
			mqtt.setCallback(this);
			mqtt.connect(connOpts);
			System.out.println("LISTENER Connected");

			mqtt.subscribe(topic, qos);

			while (true) {
				this.wait(500);
			}

		} catch (MqttException me) {
			System.out.println("LISTENER reason " + me.getReasonCode());
			System.out.println("LISTENER msg " + me.getMessage());
			System.out.println("LISTENER loc " + me.getLocalizedMessage());
			System.out.println("LISTENER cause " + me.getCause());
			System.out.println("LISTENER excep " + me);
			me.printStackTrace();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("LISTENER Connection lost!");
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("LISTENER Message arrived " + topic + " = " + message);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
	}

}
