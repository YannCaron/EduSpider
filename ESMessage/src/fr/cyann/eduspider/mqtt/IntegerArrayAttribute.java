/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

import java.util.Arrays;

/**
 <p>
 @author caronyn
 */
public class IntegerArrayAttribute extends ArrayAttribute {

	private final int[] values;

	public IntegerArrayAttribute(int[] values) {
		super(Enums.ParameterType.INTEGER_ARRAY, (short) values.length);
		this.values = values;
	}

	@Override
	public void generate(ByteBuffer buffer) {
		super.generate(buffer);

		for (int value : values) {
			buffer.append(value);
		}
	}

	@Override
	public String toString() {
		return "IntegerArray(" + Arrays.toString(values) + ")";
	}

}
