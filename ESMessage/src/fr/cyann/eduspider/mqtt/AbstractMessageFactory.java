/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

import static fr.cyann.eduspider.mqtt.Message.LENGTH_OFFSET;
import static fr.cyann.eduspider.mqtt.Message.VALUE_OFFSET;

/**
 <p>
 @author cyann
 */
public abstract class AbstractMessageFactory<T extends Message> {

	public abstract AbstractMessage<T> getNewInstance(int messageId, Enums.MessageType type);

	public AbstractMessage build(ByteBuffer buffer, int offset) {
		Enums.MessageType type = Enums.MessageType.ValueOf(buffer.get(offset + 10));
		int messageId = buffer.getInteger(offset + VALUE_OFFSET);
		AbstractMessage command = getNewInstance(messageId, type);

		int o = offset + 11;
		while (o < buffer.size()) {
			short length = buffer.getShort(o + LENGTH_OFFSET);

			Enums.ParameterType paramType = Enums.ParameterType.ValueOf(buffer.get(o));
			command.add(paramType.getBuilder().build(buffer, o));

			o += length + VALUE_OFFSET;
		}

		return command;
	}
}
