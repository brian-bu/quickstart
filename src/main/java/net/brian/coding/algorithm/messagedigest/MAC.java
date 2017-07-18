package net.brian.coding.algorithm.messagedigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

/**
 * 
 * MAC算法 (Message Authentication Codes)
 * 带秘密密钥的Hash函数：消息的散列值由只有通信双方知道的秘密密钥K来控制。此时Hash值称作MAC。
 * MAC算法原理(以直联银联pos和POS中心通讯为例）。 将欲发送给POS中心的消息中，从消息类型（MTI）到63域之间的部分构成MAC
 * ELEMEMENT BLOCK （MAB）。 对MAB，按每8个字节做异或（不管信息中的字符格式），如果最后不满8个字节，则添加“0X00”。
 *
 */

public class MAC {

	private static String src = "http://www.baidu.com";
	private static String decodeKey = "abcdefg";

	@Test
	public void jdkHmacMD5() {
		try {
			// 初始化KeyGenerator
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
			// 产生密钥
			SecretKey secretKey = keyGenerator.generateKey();
			// 获得密钥
			// byte[] keyBytes = secretKey.getEncoded();
			byte[] keyBytes = org.apache.commons.codec.binary.Hex.decodeHex(decodeKey.toCharArray());
			// 还原密钥
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacMD5");
			// 实例化MAC
			Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
			// 初始化Mac
			mac.init(secretKeySpec);
			// 执行摘要
			byte[] result = mac.doFinal(src.getBytes());
			System.out.println("jdk mac: " + Hex.toHexString(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void bcHmacMD5() {
		HMac hMac = new HMac(new MD5Digest());
		hMac.init(new KeyParameter(Hex.decode(decodeKey)));
		hMac.update(src.getBytes(), 0, src.length());

		byte[] hMacBytes = new byte[hMac.getMacSize()];
		hMac.doFinal(hMacBytes, 0);
		System.out.println("bc mac: " + Hex.toHexString(hMacBytes));
	}
}
