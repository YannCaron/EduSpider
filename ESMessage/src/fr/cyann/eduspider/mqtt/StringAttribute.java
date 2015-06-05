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
public class StringAttribute extends Attribute {

	private String value;

	public StringAttribute(String value) {
		this.value = value;
	}

	public StringAttribute(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}

	@Override
	protected byte getType() {
		return Types.STRING.getValue();
	}

	@Override
	protected short getLength() {
		return (short) (Types.STRING.getLength() * value.length());
	}

	@Override
	protected void appendData(ByteBuffer buffer) {
		buffer.append(value);
	}

	@Override
	protected final void parseData(ByteBuffer buffer, int offset) {
		short length = buffer.getShort(offset + OFFSET_LENGTH);
		int valueSize = Types.STRING.getLength();
		int size = length / valueSize;

		value = buffer.getString(offset + OFFSET_VALUE, size);
	}

	@Override
	protected void appendToString(StringBuilder builder) {
		builder.append("Integer(");
		builder.append(value);
		builder.append(')');
	}
}
