/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

import static fr.cyann.eduspider.mqtt.Enums.MessageType.values;
import static fr.cyann.eduspider.mqtt.Message.LENGTH_OFFSET;
import static fr.cyann.eduspider.mqtt.Message.VALUE_OFFSET;
import java.util.ArrayList;
import java.util.List;

/**
 <p>
 @author cyann
 */
public abstract class AbstractMessage<T extends Message> extends Message<T> {

	//private static final Enums.MessageType MESSAGE_TYPE = Enums.MessageType.COMMAND;
	//private static final short MESSAGE_LENGTH = 1;
	private final Enums.MessageType command;
	private final List<Attribute> attributes;

	public abstract Enums.MessageType getType();

	public abstract short getLength();

	public AbstractMessage(int messageId, Enums.MessageType command) {
		super(messageId);
		this.attributes = new ArrayList<Attribute>();
		this.command = command;
	}

	public AbstractMessage add(Attribute e) {
		attributes.add(e);
		return this;
	}

	@Override
	public void generate(ByteBuffer buffer) {
		super.generate(buffer);
		buffer.append(getType().getValue());
		buffer.append(getLength());
		buffer.append(command.getValue());

		for (Attribute attribute : attributes) {
			attribute.generate(buffer);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" Command(");
		sb.append(command.name());
		sb.append(")");

		int size = attributes.size();
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				sb.append(" ");
			} else {
				sb.append(", ");
			}
			sb.append(attributes.get(i).toString());
		}

		return sb.toString();
	}

}
