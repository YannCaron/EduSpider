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
public class CharAttribute extends Attribute<Character> {

	private char value;

	public CharAttribute(char value) {
		this.value = value;
	}

	public CharAttribute(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}

	@Override
	public byte getRawType() {
		return Types.CHAR.getValue();
	}

	@Override
	public short getLength() {
		return Types.CHAR.getLength();
	}

	@Override
	protected void appendData(ByteBuffer buffer) {
		buffer.append((byte) value);
	}

	@Override
	protected final void parseData(ByteBuffer buffer, int offset) {
		value = (char) buffer.get(offset + OFFSET_VALUE);
	}

	@Override
	protected void appendToString(StringBuilder builder) {
		builder.append("Char(");
		builder.append(String.valueOf(value));
		builder.append(')');
	}

	@Override
	public Character getValue() {
		return value;
	}
}
