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
public final class Tools {

	private Tools() {
		throw new RuntimeException("Cannot instanciate static class");
	}

	final protected static char[] hexArray = "0123456789abcdef".toCharArray();

	public static String bytesToPrettyHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < bytes.length; j++) {
			if (j != 0) {
				sb.append(" ");
			}
			int v = bytes[j] & 0xFF;
			sb.append(hexArray[v >>> 4]);
			sb.append(hexArray[v & 0x0F]);
		}

		return sb.toString();
	}

	public static String bytesToPrettyHex(byte b) {
		StringBuilder sb = new StringBuilder();
		int v = b & 0xFF;
		sb.append(hexArray[v >>> 4]);
		sb.append(hexArray[v & 0x0F]);

		return sb.toString();
	}
}
