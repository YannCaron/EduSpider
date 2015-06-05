/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

/**
 * <p>
 * @author cyann
 */
public class Command extends AbstractMessage<Command> {

	public Command(int messageId, Enums.MessageType command) {
		super(messageId, command);
	}
	
	@Override
	public Enums.MessageType getType() {
		return Enums.MessageType.COMMAND;
	}

	@Override
	public short getLength() {
		return 1;
	}
}
