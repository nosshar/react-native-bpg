package com.kornell.bpg;

public class DecoderWrapper {
	
	public static native int fetchDecodedBufferSize(byte[] encBuffer, int encBufferSize);
	
	public static native byte[] decodeBuffer(byte[] encBuffer, int encBufferSize);

}
