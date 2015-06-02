/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

import static fr.cyann.eduspider.mqtt.Tools.hexArray;
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

	public void append(byte v) {
		buffer.add(v);
	}

	public byte get(int i) {
		return buffer.get(i);
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

	public byte[] toArray() {
		int size = buffer.size();
		byte[] bytes = new byte[size];

		for (int i = 0; i < size; i++) {
			bytes[i] = buffer.get(i);
		}

		return bytes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int size = buffer.size();
		for (int j = 0; j < size; j++) {
			if (j != 0) {
				sb.append(" ");
			}
			int v = buffer.get(j) & 0xFF;
			sb.append(hexArray[v >>> 4]);
			sb.append(hexArray[v & 0x0F]);
		}

		return sb.toString();
	}

}
