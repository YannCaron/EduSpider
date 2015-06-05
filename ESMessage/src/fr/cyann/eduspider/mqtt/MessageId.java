package fr.cyann.eduspider.mqtt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 <p>
 @author cyann
 */
public class MessageId extends Tlv {

	private int value;

	public MessageId(int value) {
		this.value = value;
	}

	public MessageId(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}
	
	@Override
	protected byte getType() {
		return Message.Types.MESSAGE_ID.getValue();
	}

	@Override
	protected short getLength() {
		return 4;
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
		builder.append("MessageId(");
		builder.append(value);
		builder.append(')');
	}
	
}
