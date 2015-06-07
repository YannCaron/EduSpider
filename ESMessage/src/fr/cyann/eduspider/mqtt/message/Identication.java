package fr.cyann.eduspider.mqtt.message;

import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 <p>
 @author cyann
 */
public class Identication extends Tlv {

	public enum Types {

		// actions
		REGISTER((byte) 0x01),
		LIST_ALL((byte) 0x02),
		//
		// return
		RET_REGISTRATION((byte) 0xa1),
		RET_CLIENTS((byte) 0xa2);

		private final byte code;

		public byte getCode() {
			return code;
		}

		private Types(byte code) {
			this.code = code;
		}

		public static Types ValueOf(byte b) {
			for (Types code : values()) {
				if (code.code == b) {
					return code;
				}
			}
			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", ByteBuffer.byteToString(b), Types.class.getSimpleName()));
		}

	}

	private Types value;

	public Identication(Types value) {
		this.value = value;
	}

	public Identication(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}

	@Override
	public byte getRawType() {
		return Message.Types.IDENTICATION.getValue();
	}

	@Override
	public short getLength() {
		return 1;
	}

	public Types getValue() {
		return value;
	}

	@Override
	protected void appendData(ByteBuffer buffer) {
		buffer.append(value.getCode());
	}

	@Override
	protected final void parseData(ByteBuffer buffer, int offset) {
		value = Types.ValueOf(buffer.get(offset + OFFSET_VALUE));
	}

	@Override
	protected void appendToString(StringBuilder builder) {
		builder.append("Command(");
		builder.append(value);
		builder.append(')');
	}
}
