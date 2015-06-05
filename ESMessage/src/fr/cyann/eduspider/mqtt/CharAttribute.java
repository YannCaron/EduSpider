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
public class CharAttribute extends Attribute {

	private char value;

	public CharAttribute(char value) {
		this.value = value;
	}

	public CharAttribute(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}

	@Override
	protected byte getType() {
		return Types.CHAR.getValue();
	}

	@Override
	protected short getLength() {
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
}
