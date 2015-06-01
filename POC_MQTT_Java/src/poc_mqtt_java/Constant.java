/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poc_mqtt_java;

import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 <p>
 @author cyann
 */
public interface Constant {

	public static final String broker = "tcp://localhost:1883";
	public static final String topic = "MQTT Examples";
	public static final int qos = 2;
	public static final MemoryPersistence persistence = new MemoryPersistence();

}
