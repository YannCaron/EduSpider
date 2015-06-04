/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

/**
 *
 * @author caronyn
 */
public class BooleanAttribute extends Attribute {

	private final boolean value;

	public BooleanAttribute(boolean value) {
		super(Enums.ParameterType.BOOLEAN);
		this.value = value;
	}

	@Override
	public void generate(ByteBuffer buffer) {
		super.generate(buffer);
		buffer.append(value);
	}

	@Override
	public String toString() {
		return "Boolean(" + value + ")";
	}

	public static BooleanAttribute build(ByteBuffer buffer, int offset) {
		return new BooleanAttribute(buffer.getBoolean(offset + Message.VALUE_OFFSET));
	}
}
