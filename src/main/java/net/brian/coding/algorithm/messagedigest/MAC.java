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
 * MAC�㷨 (Message Authentication Codes)
 * ��������Կ��Hash��������Ϣ��ɢ��ֵ��ֻ��ͨ��˫��֪����������ԿK�����ơ���ʱHashֵ����MAC��
 * MAC�㷨ԭ��(��ֱ������pos��POS����ͨѶΪ������ �������͸�POS���ĵ���Ϣ�У�����Ϣ���ͣ�MTI����63��֮��Ĳ��ֹ���MAC
 * ELEMEMENT BLOCK ��MAB���� ��MAB����ÿ8���ֽ�����򣨲�����Ϣ�е��ַ���ʽ������������8���ֽڣ�����ӡ�0X00����
 *
 */

public class MAC {

	private static String src = "http://www.baidu.com";
	private static String decodeKey = "abcdefg";

	@Test
	public void jdkHmacMD5() {
		try {
			// ��ʼ��KeyGenerator
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
			// ������Կ
			SecretKey secretKey = keyGenerator.generateKey();
			// �����Կ
			// byte[] keyBytes = secretKey.getEncoded();
			byte[] keyBytes = org.apache.commons.codec.binary.Hex.decodeHex(decodeKey.toCharArray());
			// ��ԭ��Կ
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacMD5");
			// ʵ����MAC
			Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
			// ��ʼ��Mac
			mac.init(secretKeySpec);
			// ִ��ժҪ
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
