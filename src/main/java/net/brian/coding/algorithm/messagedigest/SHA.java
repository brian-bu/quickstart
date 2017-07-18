package net.brian.coding.algorithm.messagedigest;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.junit.Test;

/**
 * 安全哈希算法（Secure Hash Algorithm）主要适用于数字签名标准（Digital Signature Standard DSS）里面定义的
 * 数字签名算法（Digital Signature Algorithm DSA）。对于长度小于2^64位的消息，SHA1会产生一个160位的消息摘要
 * 该算法经过加密专家多年来的发展和改进已日益完善，并被广泛使用
 * 该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文，
 * 也可以简单的理解为取一串输入码（称为预映射或信息），并把它们转化为长度较短、位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程
 * 散列函数值可以说是对明文的一种“指纹”或是“摘要”所以对散列值的数字签名就可以视为对此明文的数字签名。
 * 还有更全的SHA1Digest用法见：
 * http://www.programcreek.com/java-api-examples/index.php?api=org.bouncycastle.crypto.digests.SHA1Digest
 *
 */
public class SHA {
    private static String src = "http://www.cnblogs.com/huhx";


    // jdk版本的sha算法
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
    
    // commons-codec的sha算法
    @Test
    public void bcSHA1() {
        Digest digest = new SHA1Digest();
        digest.update(src.getBytes(), 0, src.length());
        
        byte[] shaBytes = new byte[digest.getDigestSize()];
        digest.doFinal(shaBytes, 0);
        System.out.println("bc SHA 1: " + org.bouncycastle.util.encoders.Hex.toHexString(shaBytes));
    }
    
    // bcprov的sha算法
    @Test
    public void ccSHA1() {
         System.out.println("cc SHA 1: " + DigestUtils.shaHex(src.getBytes()));
    }
}
