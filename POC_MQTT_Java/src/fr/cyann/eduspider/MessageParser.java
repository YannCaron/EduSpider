/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider;

/**
 <p>
 @author cyann
 */
public class MessageParser {

	private static MessageParser singleton;
	private byte[] payload;
	private int cursor;

	public static MessageParser getInstance() {
		if (singleton == null) {
			singleton = new MessageParser();
		}
		return singleton;
	}

	private MessageParser() {
	}
	
	public void setPayload(byte[] payload) {
		this.cursor = 0;
		this.payload = payload;
	}
	
	public boolean hasNext() {
		return cursor < payload.length;
	}
	
	byte type;
	int length;
	byte[] value;
	
	public boolean readNext() {
		type = payload[cursor];
		length = ((payload[cursor + 1] & 0xff) << 8) | (payload[cursor + 2] & 0xff);
		value = new byte[length];
		System.arraycopy(payload, cursor + 3, value, 0, length);
		
		return hasNext();
	}
	
	

}
