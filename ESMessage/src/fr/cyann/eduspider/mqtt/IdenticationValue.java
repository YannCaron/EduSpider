/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

import fr.cyann.eduspider.mqtt.message.Identication;
import fr.cyann.eduspider.mqtt.message.Message;

/**
 <p>
 @author cyann
 */
public class IdenticationValue implements Rule {

	private final Identication.Types value;

	public IdenticationValue(Identication.Types value) {
		this.value = value;
	}

	@Override
	public boolean predicate(Context context) {
		if (context.getCurrentMessage().getContent().getType() != Message.Types.IDENTICATION) {
			return false;
		}
		Identication ident = (Identication)context.getCurrentMessage().getContent();
		return ident.getValue() == value;
	}

}
