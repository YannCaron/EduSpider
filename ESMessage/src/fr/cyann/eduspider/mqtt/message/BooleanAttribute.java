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
public class BooleanAttribute extends Attribute {

	private boolean value;

	public BooleanAttribute(boolean value) {
		this.value = value;
	}

	public BooleanAttribute(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}

	@Override
	protected byte getType() {
		return Types.BOOLEAN.getValue();
	}

	@Override
	protected short getLength() {
		return Types.BOOLEAN.getLength();
	}

	@Override
	protected void appendData(ByteBuffer buffer) {
		buffer.append(value);
	}

	@Override
	protected final void parseData(ByteBuffer buffer, int offset) {
		value = buffer.getBoolean(offset + OFFSET_VALUE);
	}

	@Override
	protected void appendToString(StringBuilder builder) {
		builder.append("Boolean(");
		builder.append(value);
		builder.append(')');
	}
}
