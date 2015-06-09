/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt.message;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 <p>
 @author cyann
 */
public class ByteBuffer {

	private final List<Byte> buffer;

	public ByteBuffer() {
		buffer = new ArrayList<Byte>();
	}

	public ByteBuffer(byte[] bytes) {
		buffer = new ArrayList<Byte>();

		// copy to list
		int size = bytes.length;
		for (int i = 0; i < size; i++) {
			buffer.add(bytes[i]);
		}
	}

	public int size() {
		return buffer.size();
	}

	public void append(boolean v) {
		buffer.add((byte) (v ? 0x01 : 0x00));
	}

	public boolean getBoolean(int i) {
		return buffer.get(i) == 1;
	}

	public void append(byte v) {
		buffer.add(v);
	}

	public byte get(int i) {
		return buffer.get(i);
	}

	public void append(byte[] vs) {
		for (int i = 0; i < vs.length; i++) {
			buffer.add(vs[i]);
		}
	}

	public void append(short v) {
		buffer.add((byte) (v >>> 8));
		buffer.add((byte) (v));
	}

	public short getShort(int i) {
		return (short) ((buffer.get(i) << 8) & 0x0000ff00
		  | (buffer.get(i + 1)) & 0x000000ff);
	}

	public void append(int v) {
		buffer.add((byte) (v >>> 24));
		buffer.add((byte) (v >>> 16));
		buffer.add((byte) (v >>> 8));
		buffer.add((byte) (v));
	}

	public int getInteger(int i) {
		return (buffer.get(i) << 24) & 0xff000000
		  | (buffer.get(i + 1) << 16) & 0x00ff0000
		  | (buffer.get(i + 2) << 8) & 0x0000ff00
		  | (buffer.get(i + 3)) & 0x000000ff;
	}

	public void append(String s) {
		for (byte b : s.getBytes()) {
			buffer.add(b);
		}
	}

	public String getString(int start, int length) {
		byte[] values = new byte[length];

		for (int i = 0; i < length; i++) {
			values[i] = buffer.get(start + i);
		}

		return new String(values);
	}

	public byte[] toArray() {
		int size = buffer.size();
		byte[] bytes = new byte[size];

		for (int i = 0; i < size; i++) {
			bytes[i] = buffer.get(i);
		}

		return bytes;
	}

	private final static char[] hexArray = "0123456789abcdef".toCharArray();

	@Override
	public String toString() {
		StringBuilder ascii = new StringBuilder();
		StringBuilder hexa = new StringBuilder();
		int size = buffer.size();
		for (int j = 0; j < size; j++) {
			if (j != 0) {
				if (j % 16 == 0) {
					hexa.append(" | ");
					hexa.append(ascii);
					hexa.append("\n");
					ascii.delete(0, ascii.length());
				} else if (j % 8 == 0) {
					hexa.append("  ");
					ascii.append("  ");
				} else {
					hexa.append(" ");
				}

			}
			int v = buffer.get(j) & 0xFF;
			hexa.append(hexArray[v >>> 4]);
			hexa.append(hexArray[v & 0x0F]);

			char c = (char) (buffer.get(j) & 0x00FF);
			if (c < 0x32) {
				ascii.append('.');
			} else {
				ascii.append(c);
			}
		}

		for (int i = size % 16; i < 16; i++) hexa.append("   ");
		
		hexa.append(" | ");
		hexa.append(ascii);

		return hexa.toString();
	}

	public static String byteToString(byte b) {
		StringBuilder sb = new StringBuilder();
		int v = b & 0xFF;
		sb.append(hexArray[v >>> 4]);
		sb.append(hexArray[v & 0x0F]);

		return sb.toString();
	}
}
