/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.message;

/**
 * <p>
 * @author cyann
 */
public class MessageEnums {

	public enum MessageType {

		// basic
		IDENTIFICATION((byte) 0x01),
		COMMAND((byte) 0x02),
		RESPONSE((byte) 0x03),
		ACK((byte) 0x04),
		EVENT((byte) 0x05),
		ERROR((byte) 0x06),
		IDENT((byte) 0x07),
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

}
