/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.manager;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <p>
 * @author cyann
 */
public class ManagerTest implements Constant {

	private final static Manager MANAGER = new Manager();

	public ManagerTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		MANAGER.start();
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() throws InterruptedException {
	}

	@Test
	public void test1() throws MqttException, InterruptedException {
		String broker = "tcp://localhost:1883";
		String topic = "Main";
		MqttClient sampleClient = new MqttClient(broker, "spider", new MemoryPersistence());
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		System.out.println("EMITTER Connecting to broker: " + broker);
		sampleClient.connect(connOpts);
		System.out.println("EMITTER Connected");

		StringBuilder content = new StringBuilder();
		content.append(0x01);
		content.append(4);
		content.append(5471);

		/*int[] content = new int[] {
		 0x01, 0x04, 0x00, 0x00, 0x05, 0xff
		 };*/
		sampleClient.publish(topic, content.toString().getBytes(), 2, true);

	}

}
