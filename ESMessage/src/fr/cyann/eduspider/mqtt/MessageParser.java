/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

import fr.cyann.eduspider.mqtt.MessageEnums.MessageType;

/**
 * <p>
 * @author cyann
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

	public void readNext() {
		type = MessageType.ValueOf(payload[cursor]);
		length = (short) (((payload[cursor + 1] & 0xff << 8)) | (payload[cursor + 2] & 0xff));
		value = new byte[length];

		if (length + 3 > payload.length) {
			throw new RuntimeException(String.format("Cannot parse message shorter (%d) than length (%d)", payload.length, length));
		}
		System.arraycopy(payload, cursor + 3, value, 0, length);

		cursor += length + 3;
	}

	public MessageType getType() {
		// data convertion
		return type;
	}

	public short getLength() {
		return length;
	}

	public byte[] getValue() {
		return value;
	}

	public int getIntegerValue() {
		return (value[0] << 24) & 0xff000000 |
				(value[1] << 16) & 0x00ff0000 |
				(value[2] << 8) & 0x0000ff00 |
				(value[3]) & 0x000000ff;
	}

}
