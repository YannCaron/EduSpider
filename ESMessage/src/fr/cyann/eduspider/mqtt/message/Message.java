/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt.message;

import static fr.cyann.eduspider.mqtt.message.Tlv.OFFSET_LENGTH;
import static fr.cyann.eduspider.mqtt.message.Tlv.OFFSET_VALUE;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 <p>
 @author cyann
 */
public class Message implements Generatable {

	public enum Types {

		// basic
		MESSAGE_ID((byte) 0x01, new Tlv.Factory() {

			@Override
			public Tlv create(ByteBuffer buffer, int offset) {
				return new MessageId(buffer, offset);
			}
		}),
		COMMAND((byte) 0x02, new Tlv.Factory() {

			@Override
			public Tlv create(ByteBuffer buffer, int offset) {
				return new Command(buffer, offset);
			}
		}),
		RESPONSE((byte) 0x03, new Tlv.Factory() {

			@Override
			public Tlv create(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		ACK((byte) 0x04, new Tlv.Factory() {

			@Override
			public Tlv create(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		EVENT((byte) 0x05, new Tlv.Factory() {

			@Override
			public Tlv create(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		ERROR((byte) 0x06, new Tlv.Factory() {

			@Override
			public Tlv create(ByteBuffer buffer, int offset) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		}),
		IDENTICATION((byte) 0x07, new Tlv.Factory() {

			@Override
			public Tlv create(ByteBuffer buffer, int offset) {
				return new Identication(buffer, offset);
			}
		});

		private final byte value;
		private final Tlv.Factory factory;

		public byte getValue() {
			return value;
		}

		public Tlv create(ByteBuffer buffer, int offset) {
			return factory.create(buffer, offset);
		}

		private Types(byte value, Tlv.Factory factory) {
			this.value = value;
			this.factory = factory;
		}

		public static Types valueOf(byte b) {
			for (Types value : values()) {
				if (value.value == b) {
					return value;
				}
			}

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", ByteBuffer.byteToString(b), Types.class.getSimpleName()));
		}

	}

	private final MessageId id;
	private final Tlv content;
	private final List<Attribute> attributes;
	private final static Random RND = new Random();

	public Message(MessageId id, Tlv content) {
		this.attributes = new ArrayList<Attribute>();
		this.id = id;
		this.content = content;
	}

	private static class Context {

		public final ByteBuffer buffer;
		public int offset;

		public Context(ByteBuffer buffer) {
			this.buffer = buffer;
			offset = 0;
		}

	}

	public Message(ByteBuffer buffer) {
		Context context = new Context(buffer);

		this.id = parseId(context);
		this.content = parseContent(context);
		this.attributes = parseAttributes(context);
	}

	// property
	public MessageId getId() {
		return id;
	}

	public Tlv getContent() {
		return content;
	}

		
	public Attribute getAttribute(int i) {
		return attributes.get(i);
	}
	
	public List<Attribute> getAttributes() {
		return attributes;
	}

	// method
	private MessageId parseId(Context context) {
		byte type = context.buffer.get(context.offset);
		short length = context.buffer.getShort(context.offset + OFFSET_LENGTH);

		if (type != Types.MESSAGE_ID.getValue() || length != 4) {
			throw new RuntimeException(String.format("Malformed data:\n%s", context.buffer.toString()));
		}

		MessageId id = (MessageId) Types.MESSAGE_ID.create(context.buffer, context.offset);

		context.offset += OFFSET_VALUE + length;
		return id;
	}

	private Tlv parseContent(Context context) {
		byte type = context.buffer.get(context.offset);
		short length = context.buffer.getShort(context.offset + OFFSET_LENGTH);

		Tlv cnt = Types.valueOf(type).create(context.buffer, context.offset);

		context.offset += Tlv.OFFSET_VALUE + length;
		return cnt;
	}

	private List<Attribute> parseAttributes(Context context) {
		List<Attribute> list = new ArrayList<Attribute>();

		while (context.offset < context.buffer.size()) {
			byte type = context.buffer.get(context.offset);
			short length = context.buffer.getShort(context.offset + OFFSET_LENGTH);

			list.add(Attribute.Types.ValueOf(type).create(context.buffer, context.offset));
			context.offset += OFFSET_VALUE + length;

		}

		return list;
	}

	public Message(Tlv content) {
		this(new MessageId(RND.nextInt()), content);
	}

	public Message(int id, Tlv content) {
		this(new MessageId(id), content);
	}

	public boolean addAttribute(Attribute e) {
		return attributes.add(e);
	}


	@Override
	public void generate(ByteBuffer buffer) {
		id.generate(buffer);
		content.generate(buffer);

		for (Attribute attribute : attributes) {
			attribute.generate(buffer);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		id.appendToString(sb);
		sb.append(" ");
		content.appendToString(sb);

		for (Attribute attribute : attributes) {
			sb.append(" ");
			attribute.appendToString(sb);
		}

		return sb.toString();
	}

}
