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
public abstract class Attribute implements Generatable {

	private final Enums.ParameterType type;

	public Attribute(Enums.ParameterType type) {
		this.type = type;
	}

	public short getLength() {
		return type.getLength();
	}

	@Override
	public void generate(ByteBuffer buffer) {
		buffer.append(type.getValue());
		buffer.append(getLength());
	}

}
