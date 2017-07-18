package net.brian.coding.algorithm.messagedigest;

import java.security.MessageDigest;
import java.security.Security;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

public class MD5 {
	private static String src = "http://www.cnblogs.com/huhx";

	@Test
	public void jdkMD5() {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] mdBytes = messageDigest.digest(src.getBytes());
			System.out.println("md5 decode: " + Hex.toHexString(mdBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void jdkMD2() {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD2");
			byte[] mdBytes = messageDigest.digest(src.getBytes());
			System.out.println("md2 decode: " + Hex.toHexString(mdBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bcmMD4() {
		// Digest digest = new MD4Digest();
		// digest.update(src.getBytes(), 0, src.length());
		// byte[] mdBytes = new byte[digest.getDigestSize()];
		// digest.doFinal(mdBytes, 0);
		// System.out.println("md4 decode: " + Hex.toHexString(mdBytes));

		Security.addProvider(new BouncyCastleProvider());
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD4");
			byte[] mdBytes = messageDigest.digest(src.getBytes());
			System.out.println("md4 decode: " + Hex.toHexString(mdBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//
	@Test
	public void bcmMD5() {
		Digest digest = new MD5Digest();
		digest.update(src.getBytes(), 0, src.length());
		byte[] mdBytes = new byte[digest.getDigestSize()];
		digest.doFinal(mdBytes, 0);
		System.out.println("md5 decode: " + Hex.toHexString(mdBytes));
	}

	//
	@Test
	public void ccMD5() {
		String md5String = DigestUtils.md5Hex(src.getBytes());
		System.out.println("common md5: " + md5String);
	}
}
