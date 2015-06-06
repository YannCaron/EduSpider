/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt.message;

/**
 <p>
 @author cyann
 */
public abstract class Attribute extends Tlv {

	public interface Factory {

		Attribute create(ByteBuffer buffer, int offset);
	}

	public enum Types {

		//
		// scalar parameter
		BOOLEAN((byte) 0xa1, 1, new Factory() {

			@Override
			public Attribute create(ByteBuffer buffer, int offset) {
				return new BooleanAttribute(buffer, offset);
			}
		}),
		INTEGER((byte) 0xa2, 4, new Factory() {

			@Override
			public Attribute create(ByteBuffer buffer, int offset) {
				return new IntegerAttribute(buffer, offset);
			}
		}),
		CHAR((byte) 0xa3, 1, new Factory() {

			@Override
			public Attribute create(ByteBuffer buffer, int offset) {
				return new CharAttribute(buffer, offset);
			}
		}),
		HOLE((byte) 0xa4, 1, null),
		COLLISION((byte) 0xa5, 3, null),
		//
		// scalar parameter
		//BINARY((byte) 0xb1, 1, null),
		INTEGER_ARRAY((byte) 0xb2, 4, new Factory() {

			@Override
			public Attribute create(ByteBuffer buffer, int offset) {
				return new IntegerArrayAttribute(buffer, offset);
			}
		}),
		STRING((byte) 0xb3, 1, new Factory() {

			@Override
			public Attribute create(ByteBuffer buffer, int offset) {
				return new StringAttribute(buffer, offset);
			}
		});

		private final byte value;
		private final short length;
		private final Factory factory;

		public byte getValue() {
			return value;
		}

		public short getLength() {
			return length;
		}

		public Attribute create(ByteBuffer buffer, int offset) {
			return factory.create(buffer, offset);
		}

		private Types(byte value, int length, Factory factory) {
			this.value = value;
			this.length = (short) length;
			this.factory = factory;
		}

		public static Types ValueOf(byte b) {
			for (Types value : values()) {
				if (value.value == b) {
					return value;
				}
			}

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", ByteBuffer.byteToString(b), Types.class.getSimpleName()));
		}

	}

	public Attribute() {
	}

	public Attribute(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}

}
