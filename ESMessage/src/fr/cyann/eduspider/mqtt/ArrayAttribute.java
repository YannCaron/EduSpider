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
public class ArrayAttribute extends Attribute {

	private final short count;

	public ArrayAttribute(Enums.ParameterType type, short count) {
		super(type);
		this.count = count;
	}

	@Override
	public short getLength() {
		return (short) (super.getLength() * count);
	}

}
