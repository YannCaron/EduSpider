/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.manager;

import fr.cyann.eduspider.mqtt.MessageManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Manager extends Thread implements Constant {

	@Override
	public synchronized void run() {
		MessageManager manager = new MessageManager(BROKER, MANAGER_NAME);
		manager.connect();
		manager.subscribe(TOPIC_MAIN);
		
		try {
			while (true) {
				this.wait(500);
			}
		} catch (InterruptedException ex) {
			Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
