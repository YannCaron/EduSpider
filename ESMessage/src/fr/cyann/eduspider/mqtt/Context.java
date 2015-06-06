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

	public enum Action {

		CONNECTION_LOST, MESSAGE_ARRIVED, DELIVERY_COMPLETED
	}

	public final Action currentAction;
	public final String currentTopic;
	public final Message currentMessage;

	public Context(Action currentAction, String currentTopic, Message currentMessage) {
		this.currentAction = currentAction;
		this.currentTopic = currentTopic;
		this.currentMessage = currentMessage;
	}

}
