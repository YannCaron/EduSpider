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
public class CharAttribute extends Attribute {

	private final char value;

	public CharAttribute(char value) {
		super(Enums.ParameterType.INTEGER);
		this.value = value;
	}

	@Override
	public void generate(ByteBuffer buffer) {
		super.generate(buffer);
		buffer.append(value);
	}

	@Override
	public String toString() {
		return "Char(" + value + ")";
	}

}
