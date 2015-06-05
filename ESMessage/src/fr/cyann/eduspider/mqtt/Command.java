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
public class Command extends Tlv {

	public enum Types {

		// actions
		INITIALIZE((byte) 0x01),
		MOVE_FRONT((byte) 0x02),
		MOVE_BACK((byte) 0x03),
		MOVE_LEFT((byte) 0x04),
		MOVE_RIGHT((byte) 0x05),
		ROTATE_LEFT((byte) 0x06),
		ROTATE_RIGHT((byte) 0x07),
		//
		// properties
		SET_SPEED((byte) 0xa1),
		GET_SPEED((byte) 0xa2),
		GET_DISTANCE((byte) 0xa3),
		GET_ZONE((byte) 0xa4),
		GET_ORIENTATION((byte) 0xa5),
		SET_MOTOR_ACTIVE((byte) 0xa6),
		GET_MOTOR_ACTIVE((byte) 0xa7),
		//
		// events
		ON_DETECT((byte) 0xb1),
		ON_DETECT_HOLE((byte) 0xb2);

		private final byte value;

		public byte getValue() {
			return value;
		}

		private Types(byte value) {
			this.value = value;
		}

		public static Types ValueOf(byte b) {
			for (Types value : values()) {
				if (value.value == b) {
					return value;
				}
			}

			throw new RuntimeException(String.format("Value [%s] not found on enum %s!", ByteBuffer.byteToString(b), Types.class.getSimpleName()));
		}

	}

	// events
	public static final byte ON_DETECT = (byte) 0xb1;
	public static final byte ON_DETECT_HOLE = (byte) 0xb2;

	private Types command;

	public Command(Types command) {
		this.command = command;
	}

	public Command(ByteBuffer buffer, int offset) {
		super(buffer, offset);
	}

	@Override
	protected byte getType() {
		return Message.Types.COMMAND.getValue();
	}

	@Override
	protected short getLength() {
		return 1;
	}

	@Override
	protected void appendData(ByteBuffer buffer) {
		buffer.append(command.getValue());
	}

	@Override
	protected final void parseData(ByteBuffer buffer, int offset) {
		command = Types.ValueOf(buffer.get(offset + OFFSET_VALUE));
	}

	@Override
	protected void appendToString(StringBuilder builder) {
		builder.append("Command(");
		builder.append(command);
		builder.append(')');
	}
}
