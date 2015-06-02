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
public class MessageEnums {

	public enum MessageType {

		// basic
		MESSAGE_ID((byte) 0x01),
		COMMAND((byte) 0x02),
		RESPONSE((byte) 0x03),
		ACK((byte) 0x04),
		EVENT((byte) 0x05),
		ERROR((byte) 0x06),
		IDENTIFICATION((byte) 0x07),
		//
		// scalar parameter
		BOOLEAN((byte) 0xa1),
		INTEGER((byte) 0xa2),
		CHAR((byte) 0xa3),
		HOLE((byte) 0xa4),
		COLLISION((byte) 0xa5),
		//
		// scalar parameter
		BINARY((byte) 0xb1),
		INTEGER_ARRAY((byte) 0xb2),
		STRING((byte) 0xb3);

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

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", Tools.bytesToPrettyHex(b), MessageEnums.class.getSimpleName()));
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
