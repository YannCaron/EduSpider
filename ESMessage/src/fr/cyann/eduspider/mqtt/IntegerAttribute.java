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
public class IntegerAttribute extends Attribute {

	private int value;

	public IntegerAttribute(int value) {
		this.value = value;
	}

	public IntegerAttribute(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}

	@Override
	protected byte getType() {
		return Types.INTEGER.getValue();
	}

	@Override
	protected short getLength() {
		return Types.INTEGER.getLength();
	}

	@Override
	protected void appendData(ByteBuffer buffer) {
		buffer.append(value);
	}

	@Override
	protected final void parseData(ByteBuffer buffer, int offset) {
		value = buffer.getInteger(offset + OFFSET_VALUE);
	}

	@Override
	protected void appendToString(StringBuilder builder) {
		builder.append("Integer(");
		builder.append(value);
		builder.append(')');
	}
}
