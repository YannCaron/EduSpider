/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.manager;

import fr.cyann.eduspider.mqtt.AndRule;
import fr.cyann.eduspider.mqtt.Context;
import fr.cyann.eduspider.mqtt.EventRule;
import fr.cyann.eduspider.mqtt.IdenticationValue;
import fr.cyann.eduspider.mqtt.MessageManager;
import fr.cyann.eduspider.mqtt.MessageTypeRule;
import fr.cyann.eduspider.mqtt.RuleAction;
import fr.cyann.eduspider.mqtt.message.Identication;
import fr.cyann.eduspider.mqtt.message.Message;
import fr.cyann.eduspider.mqtt.message.StringAttribute;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Manager extends Thread implements Constant {

	private final Set<String> clients;

	public Manager() {
		clients = new HashSet<String>();
	}	
	
	String registerClient(String format) {
		int i = 0;
		String name = String.format(format, i);
		while (clients.contains(name)) {
			i++;
			name = String.format(format, i);
		}
		
		// register
		clients.add(name);
		
		return name;
	}
	
	@Override
	public synchronized void run() {
		MessageManager manager = new MessageManager(BROKER, MANAGER_NAME);
		manager.connect();
		manager.subscribe(TOPIC_MAIN);
		
		manager.addRule(new RuleAction(
		  new AndRule(new EventRule(Context.Events.MESSAGE_ARRIVED),
		  new IdenticationValue(Identication.Types.REGISTER))
		) {
			
			@Override
			public void run(Context context) {
				Message answer = context.getCurrentMessage();
				StringAttribute arg1 = (StringAttribute) answer.getAttribute(0);
				
				String clientName = registerClient(arg1.getValue());
				
				Identication ident = new Identication(Identication.Types.RET_REGISTRATION);
				Message response = new Message(answer.getId(), ident);
				response.addAttribute(new StringAttribute(clientName));
				
				context.getManager().publish("Toto", response);
			}
		});
		
		try {
			while (true) {
				this.wait(500);
			}
		} catch (InterruptedException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
