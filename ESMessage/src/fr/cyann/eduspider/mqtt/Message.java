/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

/**
 <p>
 @author cyann
 @param <T>
 */
public abstract class Message<T extends Message> {

	private static final MessageEnums.MessageType MESSAGE_TYPE = MessageEnums.MessageType.MESSAGE_ID;
	private static final short MESSAGE_LENGTH = 4;
	private final int messageId;

	public Message(int messageId) {
		this.messageId = messageId;
	}

	public int getMessageId() {
		return messageId;
	}

	//public abstract T parse(int messageId, byte[] bytes);
	public void generate(ByteBuffer buffer) {
		buffer.append(MESSAGE_TYPE.getValue());
		buffer.append(MESSAGE_LENGTH);
		buffer.append(messageId);
	}

	public ByteBuffer generate() {
		ByteBuffer buffer = new ByteBuffer();
		generate(buffer);
		return buffer;
	}
}
