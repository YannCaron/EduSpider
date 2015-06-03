/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * @author cyann
 */
public class Command extends Message<Command> {

	private static final Enums.MessageType MESSAGE_TYPE = Enums.MessageType.COMMAND;
	private static final short MESSAGE_LENGTH = 1;
	private final Enums.CommandType command;
	private final List<Attribute> attributes;

	public Command(int messageId, Enums.CommandType command) {
		super(messageId);
		this.attributes = new ArrayList<Attribute>();
		this.command = command;
	}

	public Command add(Attribute e) {
		attributes.add(e);
		return this;
	}

	public static Command build(ByteBuffer buffer, int messageId) {
		Enums.CommandType type = Enums.CommandType.ValueOf(buffer.get(10));

		Command command = new Command(messageId, type);

		int c = 11;
		while (c < buffer.size()) {
			Enums.ParameterType paramType = Enums.ParameterType.ValueOf(buffer.get(c));
			short length = buffer.getShort(c + 1);

			switch (paramType) {
				case INTEGER:
					command.add(new IntegerAttribute(buffer.getInteger(c + 3)));
					break;
			}

			c += length + 3;
		}

		return command;
	}

	@Override
	public void generate(ByteBuffer buffer) {
		super.generate(buffer);
		buffer.append(MESSAGE_TYPE.getValue());
		buffer.append(MESSAGE_LENGTH);
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
