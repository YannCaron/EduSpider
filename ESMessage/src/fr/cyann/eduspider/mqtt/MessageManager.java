/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

import fr.cyann.eduspider.mqtt.message.ByteBuffer;
import fr.cyann.eduspider.mqtt.message.Message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 <p>
 @author cyann
 */
public class MessageManager implements MqttCallback {

	private final List<RuleAction> rules;
	private final MqttClient mqtt;
	private final MqttConnectOptions connOpts;
	private final Context context;

	public MessageManager(String address, String name) {
		rules = new ArrayList<RuleAction>();
		context = new Context(this);

		try {

			// create
			mqtt = new MqttClient(address, name, new MemoryPersistence());

			// config
			connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			mqtt.setCallback(this);

			//mqtt.subscribe(TOPIC_MAIN, QOS_ONCE);
		} catch (MqttException ex) {
			Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException("Cannot create mqtt client", ex);
		}

	}

	private String getLogMessage(String msgFormat, Object... args) {
		String message = String.format(msgFormat, args);
		return String.format("Manager [%s] - %s", mqtt.getClientId(), message);
	}

	public void connect() {
		try {
			Logger.getLogger(MessageManager.class.getName()).log(Level.INFO, getLogMessage("Connecting to broker [%s]", mqtt.getServerURI()));
			mqtt.connect(connOpts);
			Logger.getLogger(MessageManager.class.getName()).log(Level.INFO, getLogMessage("Connected"));
		} catch (MqttException ex) {
			Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(getLogMessage("Cannot connect to mqtt broker [%s]", mqtt.getServerURI()), ex);
		}
	}

	public void subscribe(String topic) {
		try {
			mqtt.subscribe(topic);
		} catch (MqttException ex) {
			Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(getLogMessage("Cannot subscribe to topic [%s]", topic), ex);
		}
	}

	public void addRule(RuleAction rule) {
		rules.add(rule);
	}

	public void publish(String topic, Message message) {
		ByteBuffer buffer = new ByteBuffer();
		message.generate(buffer);
		try {
			System.out.println("PUBLISH");
			mqtt.publish(topic, buffer.toArray(), 0, false);
			Logger.getLogger(MessageManager.class.getName()).log(Level.INFO, getLogMessage("Message sent, topic [%s], data [\n%s\n]", topic, buffer.toString()));
		} catch (MqttException ex) {
			Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, getLogMessage("Cannot publish message on topic [%s], data [\n%s\n]", topic, buffer.toString()), ex);
		}
	}

	private void evalRules() {
		for (RuleAction rule : rules) {
			if (rule.predicate(context)) {
				rule.run(context);
			}
		}

	}

	@Override
	public void connectionLost(Throwable cause) {
		Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, getLogMessage("Connection lost from [%s], cause [%s]", mqtt.getServerURI(), cause.getMessage()));
		cause.printStackTrace();

		context.initConnectionLost();
		evalRules();
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		ByteBuffer buffer = new ByteBuffer(message.getPayload());
		Logger.getLogger(MessageManager.class.getName()).log(Level.INFO, getLogMessage("Message received from topic [%s], data [\n%s\n]", topic, buffer.toString()));

		context.initMessageArrived(topic, new Message(buffer));
		evalRules();
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		try {
			ByteBuffer buffer = new ByteBuffer(token.getMessage().getPayload());
			Logger.getLogger(MessageManager.class.getName()).log(Level.INFO, getLogMessage("Message delivered to client [%s], topic [%s], data [\n%s\n]", token.getClient(), Arrays.toString(token.getTopics()), buffer.toString()));

			context.initDeliveryCompleted();
			evalRules();
		} catch (MqttException ex) {
			Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
