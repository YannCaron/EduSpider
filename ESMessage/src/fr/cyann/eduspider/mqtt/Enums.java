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
public class Enums {

	public enum MessageType {

		// basic
		MESSAGE_ID((byte) 0x01),
		COMMAND((byte) 0x02),
		RESPONSE((byte) 0x03),
		ACK((byte) 0x04),
		EVENT((byte) 0x05),
		ERROR((byte) 0x06),
		IDENTIFICATION((byte) 0x07);

		private final byte value;

		public byte getValue() {
			return value;
		}

		private MessageType(byte value) {
			this.value = value;
		}

		public static MessageType ValueOf(byte b) {
			for (MessageType value : values()) {
				if (value.value == b) {
					return value;
				}
			}

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", Tools.bytesToPrettyHex(b), Enums.class.getSimpleName()));
		}

	}

	public enum ParameterType {

		//
		// scalar parameter
		BOOLEAN((byte) 0xa1, 1),
		INTEGER((byte) 0xa2, 4),
		CHAR((byte) 0xa3, 1),
		HOLE((byte) 0xa4, 1),
		COLLISION((byte) 0xa5, 3),
		//
		// scalar parameter
		BINARY((byte) 0xb1, 1),
		INTEGER_ARRAY((byte) 0xb2, 4),
		STRING((byte) 0xb3, 1);

		private final byte value;
		private final short length;

		public byte getValue() {
			return value;
		}

		public short getLength() {
			return length;
		}

		private ParameterType(byte value, int length) {
			this.value = value;
			this.length = (short) length;
		}

		public static ParameterType ValueOf(byte b) {
			for (ParameterType value : values()) {
				if (value.value == b) {
					return value;
				}
			}

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", Tools.bytesToPrettyHex(b), Enums.class.getSimpleName()));
		}

	}

	public enum CommandType {

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

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", Tools.bytesToPrettyHex(b), CommandType.class.getSimpleName()));
		}

	}
}
