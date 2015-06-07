package fr.cyann.eduspider.mqtt.message;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 <p>
 @author cyann
 */
public class MessageId extends Tlv<Integer> {

	private int id;

	public MessageId(int id) {
		this.id = id;
	}

	public MessageId(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}
	
	@Override
	public byte getRawType() {
		return Message.Types.MESSAGE_ID.getValue();
	}

	@Override
	public short getLength() {
		return 4;
	}

	@Override
	public Integer getValue() {
		return id;
	}

	@Override
	protected void appendData(ByteBuffer buffer) {
		buffer.append(id);
	}

	@Override
	protected final void parseData(ByteBuffer buffer, int offset) {
		id = buffer.getInteger(offset + OFFSET_VALUE);
	}

	@Override
	protected void appendToString(StringBuilder builder) {
		builder.append("MessageId(");
		builder.append(id);
		builder.append(')');
	}
	
}
