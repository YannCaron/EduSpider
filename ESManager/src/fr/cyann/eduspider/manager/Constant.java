/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.manager;

import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 <p>
 @author cyann
 */
public interface Constant {

	public static final String BROKER = "tcp://localhost:1883";
	public static final String MANAGER_NAME = "Manager";
	public static final int QOS = 2;
	public static final String TOPIC_MAIN = "Main";
	
	public static final int QOS_AT_MOST_ONCE = 0;
	public static final int QOS_AT_LEAST_ONCE = 1;
	public static final int QOS_ONCE = 2;

}
