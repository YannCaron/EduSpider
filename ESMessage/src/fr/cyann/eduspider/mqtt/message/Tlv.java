package fr.cyann.eduspider.mqtt.message;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 <p>
 @author cyann
 * @param <T>
 */
public abstract class Tlv<T> implements Generatable {

	public interface Factory {

		Tlv create(ByteBuffer buffer, int offset);
	}

	public static final int OFFSET_LENGTH = 1;
	public static final int OFFSET_VALUE = 3;

	public Tlv() {
	}

	public Tlv(ByteBuffer buffer, int offset) {
		parseData(buffer, offset);
	}

	public abstract byte getRawType();
		
	public Message.Types getType() {
		return Message.Types.valueOf(getRawType());
	}
	
	public abstract short getLength();
	
	public abstract T getValue();
	
	protected abstract void appendData(ByteBuffer buffer);

	protected abstract void parseData(ByteBuffer buffer, int offset);

	protected abstract void appendToString(StringBuilder builder);

	@Override
	public void generate(ByteBuffer buffer) {
		buffer.append(getRawType());
		buffer.append(getLength());

		appendData(buffer);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendToString(sb);
		return sb.toString();
	}

}
