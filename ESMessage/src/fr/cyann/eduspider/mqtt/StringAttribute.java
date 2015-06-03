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
public class StringAttribute extends ArrayAttribute {

	private final String values;

	public StringAttribute(String values) {
		super(Enums.ParameterType.STRING, (short) values.length());
		this.values = values;
	}

	@Override
	public void generate(ByteBuffer buffer) {
		super.generate(buffer);

		for (byte b: values.getBytes()) {
			buffer.append(b);
		}
	}

	@Override
	public String toString() {
		return "CharArray(" + values + ")";
	}

}
