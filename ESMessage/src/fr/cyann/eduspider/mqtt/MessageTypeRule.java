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
public class MessageTypeRule implements Rule {

	private Message.Types type;

	public MessageTypeRule(Message.Types type) {
		this.type = type;
	}

	@Override
	public boolean predicate(Context context) {

		return context.getCurrentMessage().getContent().getType() == type;
	}

}
