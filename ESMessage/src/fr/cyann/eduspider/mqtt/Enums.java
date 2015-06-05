/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

import java.util.Iterator;
import java.util.List;

/**
 <p>
 @author cyann
 */
public class Enums {

	public interface Valuable {
		byte getValue();
		Valuable[] getValues();
	}
	
	public interface MessageBuilder {

		Message build(ByteBuffer buffer, int offset);
	}

	public interface AttributeBuilder {

		Attribute build(ByteBuffer buffer, int offset);
	}

	public enum MessageType {

		// basic
		MESSAGE_ID((byte) 0x01, new MessageBuilder() {

			@Override
			public Message build(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		COMMAND((byte) 0x02, new MessageBuilder() {

			@Override
			public Message build(ByteBuffer buffer, int offset) {
				return Command.build(buffer, offset);
			}
		}),
		RESPONSE((byte) 0x03, new MessageBuilder() {

			@Override
			public Message build(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		ACK((byte) 0x04, new MessageBuilder() {

			@Override
			public Message build(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		EVENT((byte) 0x05, new MessageBuilder() {

			@Override
			public Message build(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		ERROR((byte) 0x06, new MessageBuilder() {

			@Override
			public Message build(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		IDENTIFICATION((byte) 0x07, new MessageBuilder() {

			@Override
			public Message build(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		});

		private final byte value;
		private final MessageBuilder builder;

		public byte getValue() {
			return value;
		}

		public MessageBuilder getBuilder() {
			return builder;
		}

		private MessageType(byte value, MessageBuilder builder) {
			this.value = value;
			this.builder = builder;
		}

		public static MessageType ValueOf(byte b) {
			for (MessageType value : values()) {
				if (value.value == b) {
					return value;
				}
			}

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", ByteBuffer.byteToString(b), Enums.class.getSimpleName()));
		}

	}

	public enum ParameterType {

		//
		// scalar parameter
		BOOLEAN((byte) 0xa1, 1, new AttributeBuilder() {

			@Override
			public Attribute build(ByteBuffer buffer, int offset) {
				return BooleanAttribute.build(buffer, offset);
			}
		}),
		INTEGER((byte) 0xa2, 4, new AttributeBuilder() {

			@Override
			public Attribute build(ByteBuffer buffer, int offset) {
				return IntegerAttribute.build(buffer, offset);
			}
		}),
		CHAR((byte) 0xa3, 1, new AttributeBuilder() {

			@Override
			public Attribute build(ByteBuffer buffer, int offset) {
				return CharAttribute.build(buffer, offset);
			}
		}),
		HOLE((byte) 0xa4, 1, new AttributeBuilder() {

			@Override
			public Attribute build(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		COLLISION((byte) 0xa5, 3, new AttributeBuilder() {

			@Override
			public Attribute build(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		//
		// scalar parameter
		BINARY((byte) 0xb1, 1, new AttributeBuilder() {

			@Override
			public Attribute build(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		INTEGER_ARRAY((byte) 0xb2, 4, new AttributeBuilder() {

			@Override
			public Attribute build(ByteBuffer buffer, int offset) {
				return IntegerArrayAttribute.build(buffer, offset);
			}
		}),
		STRING((byte) 0xb3, 1, new AttributeBuilder() {

			@Override
			public Attribute build(ByteBuffer buffer, int offset) {
				return StringAttribute.build(buffer, offset);
			}
		});

		private final byte value;
		private final short length;
		private final AttributeBuilder builder;

		public byte getValue() {
			return value;
		}

		public short getLength() {
			return length;
		}

		public AttributeBuilder getBuilder() {
			return builder;
		}

		private ParameterType(byte value, int length, AttributeBuilder builder) {
			this.value = value;
			this.length = (short) length;
			this.builder = builder;
		}

		public static ParameterType ValueOf(byte b) {
			for (ParameterType value : values()) {
				if (value.value == b) {
					return value;
				}
			}

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", ByteBuffer.byteToString(b), Enums.class.getSimpleName()));
		}

	}

	public enum CommandType implements Valuable {

		// actions
		INITIALIZE((byte) 0x01),
		MOVE_FRONT((byte) 0x02),
		MOVE_BACK((byte) 0x03),
		MOVE_LEFT((byte) 0x04),
		MOVE_RIGHT((byte) 0x05),
		ROTATE_LEFT((byte) 0x06),
		ROTATE_RIGHT((byte) 0x07),
		//
		// properties
		SET_SPEED((byte) 0xa1),
		GET_SPEED((byte) 0xa2),
		GET_DISTANCE((byte) 0xa3),
		GET_ZONE((byte) 0xa4),
		GET_ORIENTATION((byte) 0xa5),
		SET_MOTOR_ACTIVE((byte) 0xa6),
		GET_MOTOR_ACTIVE((byte) 0xa7),
		//
		// events
		ON_DETECT((byte) 0xb1),
		ON_DETECT_HOLE((byte) 0xb2);

		private final byte value;

		@Override
		public byte getValue() {
			return value;
		}

		private CommandType(byte value) {
			this.value = value;
		}

		public static CommandType ValueOf(byte b) {
			for (CommandType value : values()) {
				if (value.value == b) {
					return value;
				}
			}

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", ByteBuffer.byteToString(b), CommandType.class.getSimpleName()));
		}

		@Override
		public Valuable[] getValues() {
			return values();
		}

	}

	public enum Identification {

		// identification
		REGISTER((byte) 0x01),
		RETURN_REGISTER((byte) 0x02);

		private final byte value;

		public byte getValue() {
			return value;
		}

		private Identification(byte value) {
			this.value = value;
		}

		public static Identification ValueOf(byte b) {
			for (Identification value : values()) {
				if (value.value == b) {
					return value;
				}
			}

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", ByteBuffer.byteToString(b), Identification.class.getSimpleName()));
		}

	}
}
