/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

/**
 <p>
 @author cyann
 */
public class Command extends Message<Command> {

	private static final MessageEnums.MessageType MESSAGE_TYPE = MessageEnums.MessageType.COMMAND;
	private static final short MESSAGE_LENGTH = 1;
	private final MessageEnums.CommandType command;

	public Command(int messageId, MessageEnums.CommandType command) {
		super(messageId);
		this.command = command;
	}

	@Override
	public void generate(ByteBuffer buffer) {
		super.generate(buffer);
		buffer.append(MESSAGE_TYPE.getValue());
		buffer.append(MESSAGE_LENGTH);
		buffer.append(command.getValue());
	}

}
