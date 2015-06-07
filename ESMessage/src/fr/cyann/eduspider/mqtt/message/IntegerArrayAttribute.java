/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt.message;

import java.util.Arrays;

/**
 <p>
 @author cyann
 */
public class IntegerArrayAttribute extends Attribute<int[]> {

	private int[] values;

	public IntegerArrayAttribute(int[] values) {
		this.values = values;
	}

	public IntegerArrayAttribute(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}

	@Override
	public byte getRawType() {
		return Types.INTEGER_ARRAY.getValue();
	}

	@Override
	public short getLength() {
		return (short) (Types.INTEGER_ARRAY.getLength() * values.length);
	}

	@Override
	protected void appendData(ByteBuffer buffer) {
		for (int value : values) {
			buffer.append(value);
		}
	}

	@Override
	protected final void parseData(ByteBuffer buffer, int offset) {
		short length = buffer.getShort(offset + OFFSET_LENGTH);
		int valueSize = Types.INTEGER_ARRAY.getLength();
		int size = length / valueSize;

		values = new int[size];
		for (int i = 0; i < size; i++) {
			values[i] = buffer.getInteger(offset + OFFSET_VALUE + (i * valueSize));
		}
	}

	@Override
	protected void appendToString(StringBuilder builder) {
		builder.append("IntegerArray(");
		builder.append(Arrays.toString(values));
		builder.append(')');
	}

	@Override
	public int[] getValue() {
		return values;
	}
}
