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
public class IntegerAttribute extends Attribute<Integer> {

	private int value;

	public IntegerAttribute(int value) {
		this.value = value;
	}

	public IntegerAttribute(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}

	@Override
	public byte getRawType() {
		return Types.INTEGER.getValue();
	}

	@Override
	public short getLength() {
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

	@Override
	public Integer getValue() {
		return value;
	}
}
