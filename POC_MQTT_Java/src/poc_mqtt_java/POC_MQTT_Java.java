/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poc_mqtt_java;

/**
 <p>
 @author cyann
 */
public class POC_MQTT_Java {

	/**
	 @param args the command line arguments
	 */
	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new Client1());
		Thread t2 = new Thread(new Client2());
		
		t1.start();
		t2.start();
		
		t1.join();
		
	}

}
