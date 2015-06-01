/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.message;

import fr.cyann.eduspider.message.MessageEnums.MessageType;

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

	MessageType type;
	short length;
	byte[] value;

	public boolean readNext() {
		type = MessageType.values()[payload[cursor]];
		length = (short) (((payload[cursor + 1] & 0xff) << 8) | (payload[cursor + 2] & 0xff));
		value = new byte[length];
		System.arraycopy(payload, cursor + 3, value, 0, length);

		return hasNext();
	}

	public MessageType getType() {
		return type;
	}

	public short getLength() {
		return length;
	}

	public byte[] getValue() {
		return value;
	}

}
