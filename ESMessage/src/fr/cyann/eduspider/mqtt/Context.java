/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

import fr.cyann.eduspider.mqtt.message.Message;

/**
 <p>
 @author cyann
 */
public class Context {

	public enum Events {

		CONNECTION_LOST, MESSAGE_ARRIVED, DELIVERY_COMPLETED
	}

	// attribute
	private final MessageManager manager;
	private Events currentEvent;
	private String currentTopic;
	private Message currentMessage;

	// constructor
	public Context(MessageManager manager) {
		this.manager = manager;
	}

	// property
	public MessageManager getManager() {
		return manager;
	}

	public Events getCurrentEvent() {
		return currentEvent;
	}

	public String getCurrentTopic() {
		return currentTopic;
	}

	public Message getCurrentMessage() {
		return currentMessage;
	}

	// method
	public void initConnectionLost() {
		currentEvent = Events.CONNECTION_LOST;
	}

	public void initMessageArrived(String topic, Message message) {
		currentEvent = Events.MESSAGE_ARRIVED;
		currentTopic = topic;
		currentMessage = message;
	}

	public void initDeliveryCompleted() {
		currentEvent = Events.DELIVERY_COMPLETED;
	}
}
