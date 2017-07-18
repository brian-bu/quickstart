package net.brian.coding.algorithm.messagedigest;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.junit.Test;

/**
 * ��ȫ��ϣ�㷨��Secure Hash Algorithm����Ҫ����������ǩ����׼��Digital Signature Standard DSS�����涨���
 * ����ǩ���㷨��Digital Signature Algorithm DSA�������ڳ���С��2^64λ����Ϣ��SHA1�����һ��160λ����ϢժҪ
 * ���㷨��������ר�Ҷ������ķ�չ�͸Ľ����������ƣ������㷺ʹ��
 * ���㷨��˼���ǽ���һ�����ģ�Ȼ����һ�ֲ�����ķ�ʽ����ת����һ�Σ�ͨ����С�����ģ�
 * Ҳ���Լ򵥵����Ϊȡһ�������루��ΪԤӳ�����Ϣ������������ת��Ϊ���Ƚ϶̡�λ���̶���������м�ɢ��ֵ��Ҳ��Ϊ��ϢժҪ����Ϣ��֤���룩�Ĺ���
 * ɢ�к���ֵ����˵�Ƕ����ĵ�һ�֡�ָ�ơ����ǡ�ժҪ�����Զ�ɢ��ֵ������ǩ���Ϳ�����Ϊ�Դ����ĵ�����ǩ����
 * ���и�ȫ��SHA1Digest�÷�����
 * http://www.programcreek.com/java-api-examples/index.php?api=org.bouncycastle.crypto.digests.SHA1Digest
 *
 */
public class SHA {
    private static String src = "http://www.cnblogs.com/huhx";


    // jdk�汾��sha�㷨
    @Test
    public void jdkSHA1() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(src.getBytes());
            byte[] shaBytes = messageDigest.digest();
             System.out.println("jdk SHA 1: " + String.valueOf(Hex.encodeHex(shaBytes)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // commons-codec��sha�㷨
    @Test
    public void bcSHA1() {
        Digest digest = new SHA1Digest();
        digest.update(src.getBytes(), 0, src.length());
        
        byte[] shaBytes = new byte[digest.getDigestSize()];
        digest.doFinal(shaBytes, 0);
        System.out.println("bc SHA 1: " + org.bouncycastle.util.encoders.Hex.toHexString(shaBytes));
    }
    
    // bcprov��sha�㷨
    @Test
    public void ccSHA1() {
         System.out.println("cc SHA 1: " + DigestUtils.shaHex(src.getBytes()));
    }
}
