/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

/**
 * <p>
 * @author cyann
 * @param <T>
 */
public abstract class Message<T extends Message> implements Generatable {

	private static final Enums.MessageType MESSAGE_TYPE = Enums.MessageType.MESSAGE_ID;
	private static final short MESSAGE_LENGTH = 4;
	private final int messageId;

	public Message(int messageId) {
		this.messageId = messageId;
	}

	public int getMessageId() {
		return messageId;
	}

	//public abstract T parse(int messageId, byte[] bytes);
	@Override
	public void generate(ByteBuffer buffer) {
		buffer.append(MESSAGE_TYPE.getValue());
		buffer.append(MESSAGE_LENGTH);
		buffer.append(messageId);
	}

	public static Message build(ByteBuffer buffer) {
		int id = buffer.getInteger(3);
		Enums.MessageType type = Enums.MessageType.ValueOf(buffer.get(7));

		switch (type) {
			case COMMAND:
				return Command.build(buffer, id);
		}

		throw new RuntimeException(String.format("Message [%s] not found to build!", type));
	}

	public ByteBuffer generate() {
		ByteBuffer buffer = new ByteBuffer();
		generate(buffer);
		return buffer;
	}

	@Override
	public String toString() {
		return "Message(" + messageId + ")";
	}

}
